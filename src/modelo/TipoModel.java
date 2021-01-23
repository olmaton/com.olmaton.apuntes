package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import modelo.intefaces.ITipoModelDao;
import modelo.intefaces.ITipoModel;

/**
 *
 * @author olmaton
 */
public class TipoModel implements ITipoModel{
    ITipoModelDao dao;
    
     public TipoModel() {
        switch (Configuracion.ORIGEN_DATOS_BD) {
            case "mysql": {
                dao = new modelo.dao.mysql.TipoDao();
                break;
            } 
            case "pgsql": {
                dao = new modelo.dao.pgsql.TipoDao();
                break;
            } 
            default: {
                break;
            }
        }
    }

    @Override
    public ArrayList<Tipo> listar(Usuario usuario) throws OlmException {
        return dao.listar(usuario);
    }

    @Override
    public boolean eliminar(Tipo tipo) throws OlmException {
        return dao.eliminar(tipo);
    }

    @Override
    public boolean agregarPorUsuario(Tipo tipo, Usuario usuario) throws OlmException {
        return dao.agregarPorUsuario(tipo, usuario);
    }

    @Override
    public boolean editarPorUsuario(Tipo tipo) throws OlmException {
        return dao.editarPorUsuario(tipo);
    }
     
    
    
}
