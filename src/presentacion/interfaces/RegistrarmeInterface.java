package presentacion.interfaces;

import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public interface RegistrarmeInterface {
    void mostrarMensaje(String mensaje,int code);    
    Usuario getUsuario();
    boolean validar();
    boolean contraseniasIguales();
    void irPrincipal();
}
