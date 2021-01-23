package modelo.dao.mysql;

import presentacion.utiles.OlmException;
import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import modelo.intefaces.IUsuarioModelDao;

/**
 *
 * @author olmaton
 */
public class UsuarioDao implements IUsuarioModelDao{

    @Override
    public boolean registrarme(Usuario usuario) throws OlmException {        
        try {           
            PreparedStatement ps = ConexionMysql.get().prepareStatement("insert into usuarios(nombres,email,password) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getEmail());            
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();    
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {         
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".registrarme");
        }
        return false;
    }
    
    @Override
    public boolean existeEmail(String email) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("select * from usuarios where email=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".existeEmail");
        }
    }

    @Override
    public Usuario login(String email, String password) throws OlmException {
        try {
            PreparedStatement ps = ConexionMysql.get().prepareStatement("select * from usuarios where email=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setCreado(rs.getObject("creado", LocalDateTime.class));
                return usuario;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".login");
        }
        return null;
    }
    
    
}
