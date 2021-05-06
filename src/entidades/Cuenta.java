package entidades;

import presentacion.utiles.Fechas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import presentacion.utiles.Montos;

/**
 *
 * @author olmaton
 */
public class Cuenta {

    private int id;
    private String nombre;
    private String descripcion;
    private LocalDateTime creado;
    private LocalDateTime actualizado;
    private int activo;
//    private Usuario usuario;

    private Double total_ingreso;
    private Double total_egreso;

    public Cuenta(int id) {
        this.id = id;
    }

    public Cuenta() {
    }

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

    public ArrayList<String[]> obtenerParametrosUrl() {
        String[] prmId = {"id", id + ""};
        ArrayList retorno = new ArrayList();
        retorno.add(prmId);
        return retorno;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Double getTotal_ingreso() {
        return total_ingreso;
    }

    public void setTotal_ingreso(Double total_ingreso) {
        this.total_ingreso = total_ingreso;
    }

    public Double getTotal_egreso() {
        return total_egreso;
    }

    public void setTotal_egreso(Double total_egreso) {
        this.total_egreso = total_egreso;
    }
    
    public Double getTotalFinal(){
        double ingreso = total_ingreso==null?0:total_ingreso;
        double egreso = total_egreso==null?0:total_egreso;
        return ingreso - egreso;
    }
    
    public String getTotalFinalFormato(){
        return Montos.formatoDosDecimales(getTotalFinal());
    }
    
    public String getTotalIngresoFormato(){
        return Montos.formatoDosDecimales(total_ingreso==null?0:total_ingreso);
    }
    
    public String getTotalEgresoFormato(){
        return Montos.formatoDosDecimales(total_egreso==null?0:total_egreso);
    }

}
