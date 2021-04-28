package modelo.intefaces;

import entidades.Usuario;
import presentacion.utiles.OlmException;

/**
 *
 * @author olmaton
 */
public interface IUsuarioModelDao {
    public boolean registrarme(Usuario usuario) throws OlmException;
    public boolean existeEmail(String email) throws OlmException;
}
