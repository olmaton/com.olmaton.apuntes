package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Usuario;
import java.util.ArrayList;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public interface ICuentaModel {

    public boolean guardar(Cuenta cuenta,Sesion sesion) throws OlmException;

    public boolean editar(Cuenta cuenta,Sesion sesion) throws OlmException;

    public boolean eliminar(Cuenta cuenta,Sesion sesion) throws OlmException;

    public ArrayList<Cuenta> listar(Sesion sesion) throws OlmException;

    public Cuenta getPorId(int id,Sesion sesion) throws OlmException;
}
