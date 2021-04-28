package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Usuario;
import modelo.dto.SesionDTO;

/**
 *
 * @author olmaton
 */
public interface IAuthModel {
    public SesionDTO login(Usuario usuario) throws OlmException;
}
