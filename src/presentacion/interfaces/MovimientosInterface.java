package presentacion.interfaces;

import java.util.ArrayList;
import entidades.Cuenta;
import entidades.Limit;
import entidades.Movimiento;
import entidades.Offset;
import entidades.Tipo;
import modelo.dto.FiltroMovimientoDTO;

/**
 *
 * @author olmaton
 */
public interface MovimientosInterface {
    void mostrarMensaje(String mensaje,int code);
    boolean confirmar(String mensaje);
    Movimiento getNuevo();
    void listar(ArrayList<Movimiento> lista);    
    void setEditar(Movimiento item);
    boolean validar();
    void limpiar();
    void llenarCuentas(ArrayList<Cuenta> lista);
    void llenarTipos(ArrayList<Tipo> lista);    
    void llenarCuentasFiltro(ArrayList<Cuenta> lista);
    void llenarTiposFiltro(ArrayList<Tipo> lista);
    FiltroMovimientoDTO getFiltro();
    //Cuenta getFiltroCuenta();
    //Tipo getFiltroTipo();
    //String getBuscar();    
    void llenarTotales(String entradas,String salidas,String total,String entradas_pre,String salidas_pre,String total_pre);
    int getLimit();
    int getOffset();
    void llenarLimits(ArrayList<Limit> lista);
    void llenarOffsets(ArrayList<Offset> lista);
    void setLabelTotal(String label);
}
