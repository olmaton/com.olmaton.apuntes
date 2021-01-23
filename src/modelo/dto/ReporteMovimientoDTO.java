package modelo.dto;

/**
 *
 * @author olmaton
 */
public class ReporteMovimientoDTO {
    private Double total_ingresos;
    private Double total_egresos;
    private int es_presupuesto;
    private int cantidad_total;

    public Double getTotal_ingresos() {
        return total_ingresos;
    }

    public void setTotal_ingresos(Double total_ingresos) {
        this.total_ingresos = total_ingresos;
    }

    public Double getTotal_egresos() {
        return total_egresos;
    }

    public void setTotal_egresos(Double total_egresos) {
        this.total_egresos = total_egresos;
    }

    public int getEs_presupuesto() {
        return es_presupuesto;
    }

    public void setEs_presupuesto(int es_presupuesto) {
        this.es_presupuesto = es_presupuesto;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }
    
    
}
