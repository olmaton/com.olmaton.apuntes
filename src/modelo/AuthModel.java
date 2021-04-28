package modelo;

import configuracion.Configuracion;
import entidades.Usuario;
import modelo.dto.SesionDTO;
import modelo.intefaces.IAuthModel;
import modelo.intefaces.IAuthModelDao;
import modelo.intefaces.ICuentaModelDao;
import presentacion.utiles.OlmException;
import servicios.Encryption;

/**
 *
 * @author olmaton
 */
public class AuthModel implements IAuthModel{

    IAuthModelDao dao;
    
     public AuthModel() {
        switch (Configuracion.ORIGEN_DATOS_BD) {
            case "mysql": {
                dao = new modelo.dao.mysql.AuthDao();
                break;
            } 
            case "pgsql": {
                dao = new modelo.dao.pgsql.AuthDao();
                break;
            } 
            default: {
                break;
            }
        }
    }
    
    @Override
    public SesionDTO login(Usuario prmUsuario) throws OlmException {        
        Usuario usuario = dao.login(prmUsuario.getEmail(), prmUsuario.getPassword());
        if(usuario==null) throw new OlmException("Email o contraseña incorrectos.", 3,this.getClass().getName()+".login");
        
        if(usuario.getPassword().equals(Encryption.getMD5(prmUsuario.getPassword()))) {
            usuario.setPassword("-");
            return new SesionDTO(usuario);
        }
        
        throw new OlmException("Email o contraseña incorrectos.", 3,this.getClass().getName()+".login");
    }
    
}
