package presentacion.interfaces;

import java.util.ArrayList;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import modelo.dto.MovimientoCuentaACuentaDTO;

/**
 *
 * @author olmaton
 */
public interface MovimientosCuentaACuentaInterface {
    void mostrarMensaje(String mensaje,int code);   
    Movimiento getMovimientoBase();
    Cuenta getCuentaOrigen();
    Cuenta getCuentaDestino();
    Tipo getTipoMovimientoOrigen();
    Tipo  getTipoMovimientoDestino();
    boolean validar();
    void limpiar();
    void llenarCuentas(ArrayList<Cuenta> lista);
    void llenarTipos(ArrayList<Tipo> lista);
    void finalizar(boolean exito);
}
