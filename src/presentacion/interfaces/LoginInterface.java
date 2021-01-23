package presentacion.interfaces;

/**
 *
 * @author olmaton
 */
public interface LoginInterface {
    void mostrarMensaje(String mensaje,int code);    
    String getPassword();
    String getEmail();
    void irPrincipal();
    boolean validar();
}
