package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Cuenta;
import entidades.Usuario;
import java.util.ArrayList;
import modelo.intefaces.ICuentaModel;
import modelo.intefaces.ICuentaModelDao;

/**
 *
 * @author olmaton
 */
public class CuentaModel implements ICuentaModel{
    ICuentaModelDao dao;
    
     public CuentaModel() {
        switch (Configuracion.ORIGEN_DATOS_BD) {
            case "mysql": {
                dao = new modelo.dao.mysql.CuentaDao();
                break;
            } 
            case "pgsql": {
                dao = new modelo.dao.pgsql.CuentaDao();
                break;
            } 
            default: {
                break;
            }
        }
    }
    @Override
    public boolean guardar(Cuenta cuenta) throws OlmException {
        return dao.guardar(cuenta);
    }

    @Override
    public boolean editar(Cuenta cuenta) throws OlmException {
        return dao.editar(cuenta);
    }

    @Override
    public boolean eliminar(Cuenta cuenta) throws OlmException {
        return dao.eliminar(cuenta);
    }

    @Override
    public ArrayList<Cuenta> listar(Usuario usuario) throws OlmException {
        return dao.listar(usuario);
    }

    @Override
    public Cuenta getPorId(int id) throws OlmException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
