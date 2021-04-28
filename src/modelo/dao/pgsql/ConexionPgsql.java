package modelo.dao.pgsql;

import modelo.dao.mysql.*;
import presentacion.utiles.OlmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author olmaton
 */
public class ConexionPgsql {

    private final static String BASE_DATOS = "olm_apuntes";
    private final static String USUARIO = "olmaton";
    private final static String PASSWORD = "olmaton";
    private static Connection cnx = null;

    public static Connection get() throws OlmException {
        if (cnx == null) {
            try {
                Class.forName("org.postgresql.Driver");
                cnx = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + BASE_DATOS, USUARIO, PASSWORD);
                boolean valid = cnx.isValid(50000);
                System.out.println(valid ? "PgSQL OK" : "PgSQL FAIL");
            } catch (ClassNotFoundException | SQLException e) {
                throw new OlmException(e.getMessage(), 3,"pgsql.Connection.get");
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
