package modelo.dao.mysql;

import presentacion.utiles.OlmException;
import entidades.Tipo;
import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.intefaces.ITipoModelDao;

/**
 *
 * @author olmaton
 */
public class TipoDao implements ITipoModelDao {


    @Override
    public ArrayList<Tipo> listar(Usuario usuario) throws OlmException {
        ArrayList<Tipo> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = ConexionMysql.get().prepareCall("select t.* from tipos t join movimientos_tipos mt on mt.id_tipo=t.id where mt.id_usuario=?;");
            ps.setInt(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setSigno(rs.getInt("signo"));
                retorno.add(tipo);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listarTipos");
        }
        return retorno;
    }

    @Override
    public Tipo getPorId(int id) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareCall("select * from tipos where id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setSigno(rs.getInt("signo"));
                return tipo;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".getTipoPorId");
        }
        return null;
    }

    private Tipo getPorNombre(String nombre) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareCall("select * from tipos where nombre=?;");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setSigno(rs.getInt("signo"));
                return tipo;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".getPorDescripcion");
        }
        return null;
    }

    @Override
    public boolean guardar(Tipo tipo) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("insert into tipos(nombre,signo) values(?,?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipo.getNombre());
            ps.setInt(2, tipo.getSigno());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tipo.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".guardar");
        }
        return false;
    }
    
    @Override
    public boolean guardarTiposUsuarios(Tipo tipo, Usuario usuario)throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("insert into tipos_usuarios(id_tipo,id_usuario) values(?,?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tipo.getId());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tipo.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".guardarTiposUsuarios");
        }
        return false;
    }

    @Override
    public boolean agregarPorUsuario(Tipo tipo, Usuario usuario) throws OlmException {
        try {
            boolean exito;
            ConexionMysql.get().setAutoCommit(false);

            Tipo existe = getPorNombre(tipo.getNombre());
            if (existe != null && existe.getSigno() == tipo.getSigno()) {
                tipo = existe;
                exito = true;
            } else {
                exito = guardar(tipo);
            }
            
            if(exito){
                exito = guardarTiposUsuarios(tipo,usuario);
            }
            
            if(exito){
                ConexionMysql.get().commit();
            }else{
                ConexionMysql.get().rollback();
            }
            
            ConexionMysql.get().setAutoCommit(true);

            return exito;
        } catch (SQLException e) {
            try {
                ConexionMysql.get().setAutoCommit(true);
            } catch (SQLException ex) {
                throw new OlmException(ex.getMessage(), 3, this.getClass().getName() + ".guardarConUsuarioCatch");
                
            }
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".guardarConUsuario");
        }
    }
    
    public boolean editarTiposUsuarios(Tipo tipo) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("update tipos_usuarios set id_tipo=?,actualizado=current_timestamp where id=?");
            ps.setInt(1, tipo.getId());
            ps.setInt(2, tipo.getId_relacion_usuario());
            return ps.executeUpdate()>0; 
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".eliminar");
        }
    }
    
    @Override
    public boolean editarPorUsuario(Tipo tipo) throws OlmException {
        try {
            boolean exito;
            ConexionMysql.get().setAutoCommit(false);

            Tipo nuevo = getPorNombre(tipo.getNombre());
            if (nuevo != null && nuevo.getSigno() == tipo.getSigno()) { 
                exito = true;
            } else {
                nuevo = new Tipo(tipo.getNombre(),tipo.getSigno());
                exito = guardar(nuevo);
            }
            
            if(exito){
                nuevo.setId_relacion_usuario(tipo.getId_relacion_usuario());
                exito = editarTiposUsuarios(nuevo);
            }
            
            if(exito){
                ConexionMysql.get().commit();
            }else{
                ConexionMysql.get().rollback();
            }
            
            ConexionMysql.get().setAutoCommit(true);

            return exito;
        } catch (SQLException e) {
            try {
                ConexionMysql.get().setAutoCommit(true);
            } catch (SQLException ex) {
                throw new OlmException(ex.getMessage(), 3, this.getClass().getName() + ".editarPorUsuarioCatch");
                
            }
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".editarPorUsuario");
        }
    }
   
    @Override
    public boolean eliminar(Tipo tipo) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("update tipos_usuarios set activo=0,actualizado=current_timestamp where id=?");
            ps.setInt(1, tipo.getId());
            return ps.executeUpdate()>0; 
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".eliminarPorUsuario");
        }
    }
    
    @Override
    public ArrayList<Tipo> listarGenerales() throws OlmException {
        ArrayList<Tipo> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = ConexionMysql.get().prepareCall("select * from tipos where general=1;");            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setSigno(rs.getInt("signo"));
                retorno.add(tipo);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listarGenerales");
        }
        return retorno;
    }

}
