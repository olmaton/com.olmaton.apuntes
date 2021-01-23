package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Usuario;

/**
 *
 * @author olmaton
 */
public interface IUsuarioModel {

    public boolean registrarme(Usuario usuario) throws OlmException;
    public boolean existeEmail(String email) throws OlmException;
    public Usuario login(String email, String contrasenia) throws OlmException;
}
