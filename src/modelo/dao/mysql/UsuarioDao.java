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
    private static String INSERT="insert into usuarios(nombres,email,password) values(?,?,?)";
    private static String SELECT_POR_EMAIL="select * from usuarios where email=?;";
    
    
    @Override
    public boolean registrarme(Usuario usuario) throws OlmException {        
        try {           
            PreparedStatement ps = ConexionMysql.get().prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement ps = ConexionMysql.get().prepareStatement(SELECT_POR_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".existeEmail");
        }
    }

    
    
    
}
