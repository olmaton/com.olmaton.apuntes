package modelo.dao.pgsql;

import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.intefaces.ICuentaModelDao;

/**
 *
 * @author olmaton
 */
public class CuentaDao implements ICuentaModelDao{

    @Override
    public boolean guardar(Cuenta cuenta) throws OlmException {        
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("insert into cuentas(nombre,descripcion,id_usuario) values(?,?,?) ",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cuenta.getNombre());
            ps.setString(2, cuenta.getDescripcion());
            ps.setInt(3, cuenta.getUsuario().getId());
            ps.executeUpdate();    
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                cuenta.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".guardar");
        }
        return false;
    }

    @Override
    public boolean editar(Cuenta cuenta) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("update cuentas set nombre=?,descripcion=?,actualizado=current_timestamp where id=?");
            ps.setString(1, cuenta.getNombre());
            ps.setString(2, cuenta.getDescripcion());
            ps.setInt(3, cuenta.getId());
            return ps.executeUpdate()>0; 
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".editar");
        }
    }

    @Override
    public boolean eliminar(Cuenta cuenta) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("update cuentas set activo=0,actualizado=current_timestamp where id=?");            
            ps.setInt(1, cuenta.getId());
            return ps.executeUpdate()>0; 
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".eliminar");
        }
    }

    @Override
    public ArrayList<Cuenta> listar(Usuario usuario) throws OlmException {
        ArrayList<Cuenta> retorno = new ArrayList<>();
        try {
            PreparedStatement ps= ConexionPgsql.get().prepareCall("select * from cuentas where activo=1 and id_usuario=? order by actualizado desc;");
            ps.setInt(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setDescripcion(rs.getString("descripcion"));
                cuenta.setCreado(rs.getObject("creado", LocalDateTime.class));
                cuenta.setActualizado(rs.getObject("actualizado", LocalDateTime.class));
                cuenta.setActivo(rs.getInt("activo"));
                retorno.add(cuenta);                
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".listar");
        }
        return retorno;
    }

    @Override
    public Cuenta getPorId(int id) throws OlmException {
        try {
            PreparedStatement ps= ConexionPgsql.get().prepareCall("select * from cuentas where id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setDescripcion(rs.getString("descripcion"));
                cuenta.setCreado(rs.getObject("creado", LocalDateTime.class));
                cuenta.setActualizado(rs.getObject("actualizado", LocalDateTime.class));
                cuenta.setActivo(rs.getInt("activo"));
                return cuenta;                
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".getPorId");
        }
        return null;
    }
    
    
}
