package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Tipo;
import java.util.ArrayList;
import servicios.Sesion;
import modelo.intefaces.ITipoModel;
import presentacion.interfaces.IMovimientoTipo;

/**
 *
 * @author olmaton
 */
public class TipoController {

    IMovimientoTipo vista;

    ITipoModel modeloTipo;
    Tipo tipo = null;
    ArrayList<Tipo> lista = new ArrayList();
    

    public TipoController(IMovimientoTipo vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                break;
            }
            case "bd": {
                modeloTipo = new modelo.TipoModel();
                break;
            }
            default: {
                break;
            }
        }
    }
    
    public void setEditar(int idx) {
        tipo = lista.get(idx);
        vista.setEditar(tipo);
    }

    public void eliminar(int idx) {
         boolean confirmar = vista.confirmar("¿Desea eliminar el tipo de movimiento: " + lista.get(idx).getNombre() + "?");
        if (confirmar) {
            try {
                confirmar = modeloTipo.eliminar(lista.get(idx));
                if (confirmar) {
                    inicializar();
                    listar();
                }
            } catch (OlmException e) {
                vista.mostrarMensaje(e.getMessage(), e.getCode());
            }
        }
    }

    public void inicializar() {
        tipo = null;
        vista.limpiar();
    }

    public void listar() {
        try {
            lista = modeloTipo.listar(Sesion.getInstancia().getUsuario());
            vista.listar(lista);
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }
    
    public void procesar() {
        if (vista.validar() == false) {
            return;
        }

        boolean procesado = false;
        try {
            Tipo nuevo = vista.getNuevo();
            if (tipo == null) {
                //Nuevo 
                procesado = modeloTipo.agregarPorUsuario(nuevo,Sesion.getInstancia().getUsuario());
            } else {
                //Editar               
                tipo.setNombre(nuevo.getNombre());
                tipo.setSigno(nuevo.getSigno());             
                procesado = modeloTipo.editarPorUsuario(tipo);
            }
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

        if (procesado) {
            inicializar();
            listar();
        } else {
            vista.mostrarMensaje("No se pudo procesar.", 2);
        }

    }

}
