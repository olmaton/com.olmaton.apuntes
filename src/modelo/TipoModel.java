package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Tipo;
import java.util.ArrayList;
import modelo.intefaces.ITipoModelDao;
import modelo.intefaces.ITipoModel;
import servicios.Sesion;

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
    public ArrayList<Tipo> listar(Sesion sesion) throws OlmException {
        return dao.listar(sesion.getUsuario());
    }

    @Override
    public boolean eliminar(Tipo tipo,Sesion sesion) throws OlmException {
        return dao.eliminar(tipo);
    }

    @Override
    public boolean agregarPorUsuario(Tipo tipo, Sesion sesion) throws OlmException {
        return dao.agregarPorUsuario(tipo, sesion.getUsuario());
    }

    @Override
    public boolean editarPorUsuario(Tipo tipo,Sesion sesion) throws OlmException {
        return dao.editarPorUsuario(tipo);
    }
     
    
    
}
