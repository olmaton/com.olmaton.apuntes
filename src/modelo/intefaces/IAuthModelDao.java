package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public interface IAuthModelDao {

    public Usuario login(String email, String contrasenia) throws OlmException;
}
