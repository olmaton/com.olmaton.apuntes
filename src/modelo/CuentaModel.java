package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Cuenta;
import java.util.ArrayList;
import modelo.intefaces.ICuentaModel;
import modelo.intefaces.ICuentaModelDao;
import servicios.Sesion;

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
    public boolean guardar(Cuenta cuenta,Sesion sesion) throws OlmException {
        return dao.guardar(cuenta,sesion.getUsuario());
    }

    @Override
    public boolean editar(Cuenta cuenta,Sesion sesion) throws OlmException {
        return dao.editar(cuenta);
    }

    @Override
    public boolean eliminar(Cuenta cuenta,Sesion sesion) throws OlmException {
        return dao.eliminar(cuenta);
    }

    @Override
    public ArrayList<Cuenta> listar(Sesion sesion) throws OlmException {
        return dao.listar(sesion.getUsuario());
    }

    @Override
    public Cuenta getPorId(int id,Sesion sesion) throws OlmException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
