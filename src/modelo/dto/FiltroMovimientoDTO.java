package modelo.dto;

import entidades.Cuenta;
import entidades.Tipo;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olmaton
 */
public class FiltroMovimientoDTO {

    private Cuenta cuenta;
    private Tipo tipo;
    private int movimientoOPresupueso;
    private String buscar;
    private int limit;
    private int offset = 0;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getMovimientoOPresupueso() {
        return movimientoOPresupueso;
    }

    public void setMovimientoOPresupueso(int movimientoOPresupueso) {
        this.movimientoOPresupueso = movimientoOPresupueso;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public ArrayList<String[]> obtenerParametrosUrl() {
        String[] prmBuscar = {"buscar",buscar};
        String[] prmCuenta = {"id_cuenta",cuenta.getId()+""};
        String[] prmTipo = {"id_tipo",tipo.getId()+""};
        String[] prmEsPresupuesto = {"es_presupuesto",movimientoOPresupueso+""};
        String[] prmLimit = {"limit",limit+""};
        String[] prmOfset = {"offset",offset+""};
        
        
        ArrayList retorno = new ArrayList();
        retorno.add(prmBuscar);
        retorno.add(prmCuenta);
        retorno.add(prmTipo);
        retorno.add(prmEsPresupuesto);
        retorno.add(prmLimit);
        retorno.add(prmOfset);
        return retorno;
    }
}
