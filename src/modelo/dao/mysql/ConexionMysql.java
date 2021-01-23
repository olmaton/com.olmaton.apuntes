package modelo.dao.mysql;

import presentacion.utiles.OlmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author olmaton
 */
public class ConexionMysql {

    private final static String BASE_DATOS = "olm_apuntes";
    private final static String USUARIO = "olm_apuntes";
    private final static String PASSWORD = "olm_apuntes";

    private static Connection cnx = null;

    public static Connection get() throws OlmException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/" + BASE_DATOS + "?noAccessToProcedureBodies=true&useSSL=false", USUARIO, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                throw new OlmException(e.getMessage(), 3,"mysql.Connection.get");
            }
        }

        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }
}
