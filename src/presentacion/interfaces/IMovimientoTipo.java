package presentacion.interfaces;

import java.util.ArrayList;
import entidades.Tipo;

/**
 *
 * @author olmaton
 */
public interface IMovimientoTipo {
    void mostrarMensaje(String mensaje,int code);
    boolean confirmar(String mensaje);
    Tipo getNuevo();
    void listar(ArrayList<Tipo> lista); 
    void limpiar();
    void setEditar(Tipo tipo);
    boolean validar();
    
}
