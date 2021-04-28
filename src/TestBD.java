
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.pgsql.ConexionPgsql;
import presentacion.utiles.OlmException;

public class TestBD {

    
    public static void main(String[] args) {
        try {
             
            
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("SELECT * FROM public.usuario");         
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("nombres"));
            }
      
            
        } catch (OlmException ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
