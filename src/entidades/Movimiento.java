package entidades;

import presentacion.utiles.Fechas;
import entidades.Cuenta;
import java.time.LocalDateTime;
import presentacion.utiles.Montos;

/**
 *
 * @author olmaton
 */
public class Movimiento {
    
    private int id;
    private String nombre;
    private String descripcion;
    private Tipo tipo;
    private Cuenta cuenta;
    private LocalDateTime fecha_hora;
    private Double valor_unitario_ingreso;
    private Double valor_unitario_egreso;
    private int cantidad;
    private int es_presupuesto;
    private LocalDateTime creado;
    private LocalDateTime actualizado;
    private int activo;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    
    public String getFechaHoraFormato() {        
        return Fechas.setFechaHoraCortaFromLocalDateTime(fecha_hora);
    }

    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getActualizado() {
        return actualizado;
    }

    public void setActualizado(LocalDateTime actualizado) {
        this.actualizado = actualizado;
    }
    
    public String getActualizadoFormato() {        
        return Fechas.setFechaHoraCortaFromLocalDateTime(actualizado);
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getEs_presupuesto() {
        return es_presupuesto;
    }

    public void setEs_presupuesto(int es_presupuesto) {
        this.es_presupuesto = es_presupuesto;
    }

    public Double getValor_unitario_ingreso() {
        return valor_unitario_ingreso;
    }

    public void setValor_unitario_ingreso(Double valor_unitario_ingreso) {
        this.valor_unitario_ingreso = valor_unitario_ingreso;
    }

    public Double getValor_unitario_egreso() {
        return valor_unitario_egreso;
    }

    public void setValor_unitario_egreso(Double valor_unitario_egreso) {
        this.valor_unitario_egreso = valor_unitario_egreso;
    }

    public String getValor_unitario_egresoFormato() {
       return Montos.formatoDosDecimales(valor_unitario_egreso);
    }
    
    public String getValor_unitario_ingresoFormato() {
       return Montos.formatoDosDecimales(valor_unitario_ingreso);
    }
    
    public String getMontoFormato(){
        return Montos.formatoDosDecimales((valor_unitario_ingreso*cantidad)+(valor_unitario_egreso*cantidad));
    }
    
    public void validarIngresoEgreso(){
        if(tipo.getSigno()>0){
            this.valor_unitario_egreso = 0.0;
        }else{
            this.valor_unitario_ingreso = 0.0;
        }
    }
}
