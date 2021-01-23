package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Movimiento;
import java.util.ArrayList;
import presentacion.interfaces.CuentasInterface;
import servicios.Sesion;
import modelo.intefaces.ICuentaModel;

/**
 *
 * @author olmaton
 */
public class CuentaController {

    CuentasInterface vista;
    ICuentaModel modelo;
    Cuenta cuenta = null;
    ArrayList<Cuenta> lista = new ArrayList();

    public CuentaController(CuentasInterface vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                //Es un ejemplo(Sin funcionalidad)
                modelo = new modelo.api.CuentaModel();
                break;
            }
            case "bd": {
                modelo = new modelo.CuentaModel();
                break;
            }
            default: {
                modelo = null;
                break;
            }
        }
    }

    public void setEditar(int idx) {
        cuenta = lista.get(idx);
        vista.setEditar(cuenta);
    }

    public void eliminar(int idx) {
        boolean confirmar = vista.confirmar("Al eliminar una cuenta, todos los movimientos asociados también se eliminaran.<br>¿Desea eliminar la cuenta: " + lista.get(idx).getNombre() + "?");
        if (confirmar) {
            try {
                confirmar = modelo.eliminar(lista.get(idx));
                if(confirmar){
                    inicializar();
                    listar();
                }
            } catch (OlmException e) {
                vista.mostrarMensaje(e.getMessage(), e.getCode());
            }
        }
    }

    public void inicializar() {
        cuenta = null;
        vista.inicializar();
    }

    public void listar() {
        lista = new ArrayList();
        try {
            lista = modelo.listar(Sesion.getInstancia().getUsuario());
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
        vista.listar(lista);
    }

    public void procesar() {
        if (vista.validar() == false) return;
        
        boolean procesado = false;
        if (cuenta == null) {
            //Nuevo
            Cuenta nueva = vista.getNuevo();
            nueva.setUsuario(Sesion.getInstancia().getUsuario());
            try {
                procesado = modelo.guardar(nueva);
            } catch (OlmException e) {
                vista.mostrarMensaje(e.getMessage(), e.getCode());
            }
        } else {
            //Editar
            Cuenta nueva = vista.getNuevo();
            cuenta.setNombre(nueva.getNombre());
            cuenta.setDescripcion(nueva.getDescripcion());
            cuenta.setUsuario(Sesion.getInstancia().getUsuario());
            try {
                procesado = modelo.editar(cuenta);                
            } catch (OlmException e) {
                vista.mostrarMensaje(e.getMessage(), e.getCode());
            }
        }

        if (procesado) {
            inicializar();
            listar();
        } else {
            vista.mostrarMensaje("No se pudo procesar.", 2);
        }

    }
    
    

}
