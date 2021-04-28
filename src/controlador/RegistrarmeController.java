package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Usuario;
import modelo.UsuarioModel;
import modelo.api.UsuarioApiModel;
import presentacion.interfaces.RegistrarmeInterface;
import modelo.intefaces.IUsuarioModel;

/**
 *
 * @author olmaton
 */
public class RegistrarmeController {

    RegistrarmeInterface vista;
    IUsuarioModel modelo;

    public RegistrarmeController(RegistrarmeInterface vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                modelo = new UsuarioApiModel();
                break;
            }
            case "bd": {
                modelo = new UsuarioModel();
                break;
            }
            default: {
                modelo = null;
                break;
            }
        }
    }

    public void registrarme() {
        if (vista.validar() == false) {
            return;
        }
        if (vista.contraseniasIguales() == false) {
            vista.mostrarMensaje("Las contraseñas no son iguales.", 3);
            return;
        }

        boolean procesado = false;
        try {
            Usuario usuario = vista.getUsuario();
            procesado = modelo.registrarme(usuario);
            
            if(procesado){
                vista.mostrarMensaje("¡Registro exitoso!<br>Ya puede usar su cuenta para ingresar al sistema.", 0);
            }
            
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

        if (procesado) {
            vista.irLogin();
        }

    }

}
