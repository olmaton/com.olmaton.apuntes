package entidades;

/**
 *
 * @author olmaton
 */
public class Offset {
    private int factor;
    private String descripcion;

    public Offset(int factor, String descripcion) {
        this.factor = factor;
        this.descripcion = descripcion;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     @Override
    public String toString() {
        return descripcion;
    }
    
}
