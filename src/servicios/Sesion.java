package servicios;

import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public class Sesion {
    private static Sesion instancia;
    private static Usuario usuario;
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
    
    
}
