package servicios;

import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public class Sesion {
    private static Sesion instancia;
    private static Usuario usuario;
    private static String token="";
    private static String token_refresh="";
  
    
    private Sesion(){        
    }
    
    
    public static Sesion getInstancia(){
           if(instancia==null) {
               instancia = new Sesion();
           }
           return instancia;
    }
 
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        Sesion.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        Sesion.token = token;
    }

    public String getToken_refresh() {
        return token_refresh;
    }

    public void setToken_refresh(String token_refresh) {
        Sesion.token_refresh = token_refresh;
    }
    
    
    
}
