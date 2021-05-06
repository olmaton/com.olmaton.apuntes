package controlador;

import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Limit;
import entidades.Movimiento;
import entidades.Offset;
import entidades.Tipo;
import java.util.ArrayList;
import modelo.CuentaModel;
import modelo.MovimientoModel;
import modelo.TipoModel;
import modelo.api.CuentaApiModel;
import modelo.api.MovimientoApiModel;
import modelo.api.TipoApiModel;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.MovimientoCuentaACuentaDTO;
import modelo.dto.ReporteMovimientoDTO;
import presentacion.interfaces.MovimientosInterface;
import presentacion.utiles.Montos;
import servicios.Sesion;
import modelo.intefaces.ICuentaModel;
import modelo.intefaces.IMovimientosModel;
import modelo.intefaces.ITipoModel;
import presentacion.interfaces.MovimientosCuentaACuentaInterface;

/**
 *
 * @author olmaton
 */
public class MovimientoCuentaACuentaController {

    MovimientosCuentaACuentaInterface vista;

    IMovimientosModel modeloMovimiento;
    ICuentaModel modeloCuenta;
    ITipoModel modeloTipo;

    public MovimientoCuentaACuentaController(MovimientosCuentaACuentaInterface vista) {
        this.vista = vista;
        switch (Configuracion.ORIGEN_DATOS) {
            case "api": {
                modeloTipo = new TipoApiModel();
                modeloCuenta = new CuentaApiModel();
                modeloMovimiento = new MovimientoApiModel();
                break;
            }
            case "bd": {
                modeloMovimiento = new MovimientoModel();
                modeloCuenta = new CuentaModel();
                modeloTipo = new TipoModel();
                break;
            }
            default: {
                break;
            }
        }

    }

    public void inicializar() {
        vista.limpiar();
    }

    public void listarCuentas() {
        try {
            ArrayList<Cuenta> cuentas = modeloCuenta.listar(Sesion.getInstancia());
            vista.llenarCuentas(cuentas);

        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    public void listarTipos() {
        try {
            ArrayList<Tipo> tipos = modeloTipo.listar(Sesion.getInstancia());
            vista.llenarTipos(tipos);
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    private Movimiento generarNuevoMovimiento(Movimiento nuevo, Cuenta cuenta, Tipo tipo) {
        Movimiento movimiento = new Movimiento();
        movimiento.setNombre(nuevo.getNombre());
        movimiento.setDescripcion(nuevo.getDescripcion());
        movimiento.setCantidad(nuevo.getCantidad());
        movimiento.setFecha_hora(nuevo.getFecha_hora());
        movimiento.setValor_unitario_egreso(nuevo.getValor_unitario_ingreso());
        movimiento.setValor_unitario_ingreso(nuevo.getValor_unitario_egreso());
        movimiento.setEs_presupuesto(nuevo.getEs_presupuesto());
        movimiento.setCuenta(cuenta);
        movimiento.setTipo(tipo);
        movimiento.validarIngresoEgreso();
        return movimiento;
    }

    public void procesar() {
        if (vista.validar() == false) {
            return;
        }

        boolean procesado = false;
        try {
            Movimiento nuevo = vista.getMovimientoBase();
            Movimiento origen = generarNuevoMovimiento(nuevo, vista.getCuentaOrigen(), vista.getTipoMovimientoOrigen());
            Movimiento hasta = generarNuevoMovimiento(nuevo, vista.getCuentaDestino(), vista.getTipoMovimientoDestino());

            MovimientoCuentaACuentaDTO dto = new MovimientoCuentaACuentaDTO();
            dto.setOrigen(origen);
            dto.setDestino(hasta);

            procesado = modeloMovimiento.moverEntreCuentas(dto, Sesion.getInstancia());

        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

        if (procesado) {
            inicializar();
            vista.finalizar(true);
        } else {
            vista.mostrarMensaje("No se pudo procesar.", 2);
        }

    }


}
