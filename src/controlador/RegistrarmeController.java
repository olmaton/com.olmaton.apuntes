package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Usuario;
import java.util.ArrayList;
import presentacion.FrmPrincipal;
import presentacion.interfaces.CuentasInterface;
import presentacion.interfaces.RegistrarmeInterface;
import servicios.Sesion;
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
                Sesion.getInstancia().setUsuario(usuario);
            }
            
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

        if (procesado) {
            vista.irPrincipal();
        }

    }

}
