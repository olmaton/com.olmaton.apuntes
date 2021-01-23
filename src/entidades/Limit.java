package entidades;

/**
 *
 * @author olmaton
 */
public class Limit {
    private String descripcion;
    private int cantidad;

    public Limit(int cantidad,String descripcion) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
    
}
