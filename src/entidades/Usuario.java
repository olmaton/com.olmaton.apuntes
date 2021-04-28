package entidades;

import java.time.LocalDateTime;

/**
 *
 * @author olmaton
 */
public class Usuario {
    private int id;
    private String nombres;
    private String email;
    private String password;
    private LocalDateTime creado;    
private Aplicacion origen;
    public Usuario() {
    }

    
    
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Aplicacion getOrigen() {
        return origen;
    }

    public void setOrigen(Aplicacion origen) {
        this.origen = origen;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }
}
