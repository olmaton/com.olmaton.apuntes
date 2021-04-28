package modelo.dto;

import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public class SesionDTO {
    private Usuario usuario;
    private String token="";
    private String token_refresh="";

    public SesionDTO() {
    }

    public SesionDTO(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_refresh() {
        return token_refresh;
    }

    public void setToken_refresh(String token_refresh) {
        this.token_refresh = token_refresh;
    }
    
}
