package controlador;

import configuracion.Configuracion;
import entidades.Aplicacion;
import presentacion.utiles.OlmException;
import entidades.Usuario;
import modelo.AuthModel;
import modelo.UsuarioModel;
import modelo.api.AuthApiModel;
import modelo.dto.SesionDTO;
import modelo.intefaces.IAuthModel;
import presentacion.interfaces.LoginInterface;
import servicios.Sesion;
import modelo.intefaces.IUsuarioModel;

/**
 *
 * @author olmaton
 */
public class AuthController {

    LoginInterface vista;
    IAuthModel modelo;

    public AuthController(LoginInterface vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                modelo = new AuthApiModel();
                break;
            }
            case "bd": {
                modelo = new AuthModel();
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
            Usuario usuario = new Usuario(email,password);
            usuario.setOrigen(new Aplicacion(Configuracion.APP_NOMBRE, Configuracion.APP_CODIGO, Configuracion.APP_DESCRIPCION));
            SesionDTO sesion = modelo.login(usuario);           
            
            if(sesion!=null){
                Sesion.getInstancia().setUsuario(sesion.getUsuario());
                Sesion.getInstancia().setToken(sesion.getToken());
                Sesion.getInstancia().setToken_refresh(sesion.getToken_refresh());
                vista.irPrincipal();
            }
            
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

    }

}
