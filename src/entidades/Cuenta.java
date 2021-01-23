package entidades;



import presentacion.utiles.Fechas;
import java.time.LocalDateTime;

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
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
