package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Usuario;
import presentacion.interfaces.LoginInterface;
import servicios.Sesion;
import modelo.intefaces.IUsuarioModel;

/**
 *
 * @author olmaton
 */
public class LoginController {

    LoginInterface vista;
    IUsuarioModel modelo;

    public LoginController(LoginInterface vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                break;
            }
            case "bd": {
                modelo = new modelo.UsuarioModel();
                break;
            }
            default: {
                modelo = null;
                break;
            }
        }
    }

    public void login() {
       
        try {
            String email = vista.getEmail();
            String password = vista.getPassword();
            Usuario usuario = modelo.login(email,password);
            
            if(usuario!=null){
                Sesion.getInstancia().setUsuario(usuario);
                vista.irPrincipal();
            }
            
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

    }

}
