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
import modelo.dto.ReporteMovimientoDTO;
import presentacion.interfaces.MovimientosInterface;
import presentacion.utiles.Montos;
import servicios.Sesion;
import modelo.intefaces.ICuentaModel;
import modelo.intefaces.IMovimientosModel;
import modelo.intefaces.ITipoModel;

/**
 *
 * @author olmaton
 */
public class MovimientoController {

    MovimientosInterface vista;

    IMovimientosModel modeloMovimiento;
    ICuentaModel modeloCuenta;
    ITipoModel modeloTipo;

    Movimiento movimiento = null;
    ArrayList<Movimiento> lista = new ArrayList();
    ArrayList<Limit> listaLimites = new ArrayList();

    public MovimientoController(MovimientosInterface vista) {
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
        //listaLimites.add(new Limit(5, "Mostrar 5"));
        listaLimites.add(new Limit(50, "Mostrar 50"));
        listaLimites.add(new Limit(100, "Mostrar 100"));
        listaLimites.add(new Limit(500, "Mostrar 500"));
        listaLimites.add(new Limit(1000, "Mostrar 1000"));
    }

    public void setEditar(int idx) {
        movimiento = lista.get(idx);
        vista.setEditar(movimiento);
    }

    public void eliminar(int idx) {
        boolean confirmar = vista.confirmar("¿Desea eliminar el movimiento: " + lista.get(idx).getNombre() + "?");
        if (confirmar) {
            try {
                confirmar = modeloMovimiento.eliminar(lista.get(idx),Sesion.getInstancia());
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
        movimiento = null;
        vista.limpiar();
    }

    private int cantidadTotal(ArrayList<ReporteMovimientoDTO> lista) {
        int total = 0;
        for (ReporteMovimientoDTO dto : lista) {
            total += dto.getCantidad_total();
        }
        return total;
    }

    public void listar() {
        try {
            FiltroMovimientoDTO sfm = vista.getFiltro();
            ArrayList<ReporteMovimientoDTO> reporte = modeloMovimiento.reporteConsulta(Sesion.getInstancia(), sfm);
            lista = modeloMovimiento.listar(Sesion.getInstancia(), sfm);

            procesarReporte(reporte);
            vista.listar(lista);

            int total = cantidadTotal(reporte);
            procesarLlenarOffsets(sfm.getLimit(), total);
            setLabelTotal(total);

        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    public void listarOffset() {
        try {
            FiltroMovimientoDTO sfm = vista.getFiltro();
            lista = modeloMovimiento.listar(Sesion.getInstancia(), sfm);
            vista.listar(lista);
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    private void procesarReporte(ArrayList<ReporteMovimientoDTO> lista) {
        double ingresos = 0;
        double salidas = 0;
        double ingresos_presupuestos = 0;
        double salidas_presupuestos = 0;

        for (ReporteMovimientoDTO m : lista) {
            if (m.getEs_presupuesto() == 1) {
                ingresos_presupuestos += m.getTotal_ingresos();
                salidas_presupuestos += m.getTotal_egresos();
            } else {
                ingresos += m.getTotal_ingresos();
                salidas += m.getTotal_egresos();
            }
        }
        double total = ingresos - salidas;
        double total_presupuestos = ingresos_presupuestos - salidas_presupuestos;
        vista.llenarTotales(
                Montos.formatoDosDecimales(ingresos),
                Montos.formatoDosDecimales(salidas),
                Montos.formatoDosDecimales(total),
                Montos.formatoDosDecimales(ingresos_presupuestos),
                Montos.formatoDosDecimales(salidas_presupuestos),
                Montos.formatoDosDecimales(total_presupuestos)
        );
        vista.llenarTotalGeneral(Montos.formatoDosDecimales(total+total_presupuestos));
        
    }

    public void listarCuentas() {
        try {
            ArrayList<Cuenta> cuentas = modeloCuenta.listar(Sesion.getInstancia());
            vista.llenarCuentas(cuentas);
            vista.llenarCuentasFiltro(cuentas);

        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    public void listarTipos() {
        try {
            ArrayList<Tipo> tipos = modeloTipo.listar(Sesion.getInstancia());
            vista.llenarTipos(tipos);
            vista.llenarTiposFiltro(tipos);
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }
    }

    public void llenarLimits() {
        vista.llenarLimits(listaLimites);
    }

    private void setLabelTotal(int total) {
        vista.setLabelTotal("Total de registros: " + total);
    }

    public void procesarLlenarOffsets(int limit, int total) {
        ArrayList<Offset> offsets = new ArrayList<>();
        if (total == 0) {
            vista.llenarOffsets(offsets);
            return;
        }

        int paginasCompletas = total / limit;
        int paginasIncompletas = total % limit > 0 ? 1 : 0;

        for (int i = 0; i < paginasCompletas; i++) {
            offsets.add(crearOffset(i, (i + 1), paginasCompletas + paginasIncompletas));
        }

        if (paginasIncompletas > 0) {
            offsets.add(crearOffset(offsets.size(), offsets.size() + 1, paginasCompletas + paginasIncompletas));
        }
        vista.llenarOffsets(offsets);
    }

    private Offset crearOffset(int factor, int pagina, int total) {
        return new Offset(factor, "Página " + pagina + " de " + total);
    }

    public void procesar() {
        if (vista.validar() == false) {
            return;
        }

        boolean procesado = false;
        try {
            Movimiento nuevo = vista.getNuevo();
            if (movimiento == null) {
                //Nuevo            
                nuevo.validarIngresoEgreso();
                procesado = modeloMovimiento.guardar(nuevo,Sesion.getInstancia());
            } else {
                //Editar               
                movimiento.setNombre(nuevo.getNombre());
                movimiento.setDescripcion(nuevo.getDescripcion());
                movimiento.setCantidad(nuevo.getCantidad());
                movimiento.setFecha_hora(nuevo.getFecha_hora());
                movimiento.setCuenta(nuevo.getCuenta());
                movimiento.setTipo(nuevo.getTipo());
                movimiento.setValor_unitario_egreso(nuevo.getValor_unitario_ingreso());
                movimiento.setValor_unitario_ingreso(nuevo.getValor_unitario_egreso());
                movimiento.setEs_presupuesto(nuevo.getEs_presupuesto());
                movimiento.validarIngresoEgreso();
                procesado = modeloMovimiento.editar(movimiento,Sesion.getInstancia());
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

    public void actualizarEsPresupuesto(int idx, boolean esPresupuesto) {
        boolean procesado = false;
        try {
            Movimiento editar = lista.get(idx);
            editar.setEs_presupuesto(esPresupuesto ? 1 : 0);
            System.out.println(editar.getEs_presupuesto() + "|" + idx + "|" + esPresupuesto);
            procesado = modeloMovimiento.editar(editar,Sesion.getInstancia());
        } catch (OlmException e) {
            vista.mostrarMensaje(e.getMessage(), e.getCode());
        }

        if (procesado) {
            listar();
        } else {
            vista.mostrarMensaje("No se pudo procesar.", 2);
        }

    }

}
