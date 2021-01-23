package modelo.dto;

import entidades.Cuenta;
import entidades.Tipo;
import java.time.LocalDateTime;

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
    private int offset=0;
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
}
