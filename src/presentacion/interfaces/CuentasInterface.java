package presentacion.interfaces;

import java.util.ArrayList;
import entidades.Cuenta;

/**
 *
 * @author olmaton
 */
public interface CuentasInterface {
    void mostrarMensaje(String mensaje,int code);
    boolean confirmar(String mensaje);
    Cuenta getNuevo();
    void listar(ArrayList<Cuenta> lista);    
    void setEditar(Cuenta item);
    boolean validar();
    void inicializar();
    
}
