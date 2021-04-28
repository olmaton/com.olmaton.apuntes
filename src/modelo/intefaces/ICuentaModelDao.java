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
public interface ICuentaModelDao {

    public boolean guardar(Cuenta cuenta,Usuario usuario) throws OlmException;

    public boolean editar(Cuenta cuenta) throws OlmException;

    public boolean eliminar(Cuenta cuenta) throws OlmException;

    public ArrayList<Cuenta> listar(Usuario usuario) throws OlmException;

    public Cuenta getPorId(int id) throws OlmException;
}
