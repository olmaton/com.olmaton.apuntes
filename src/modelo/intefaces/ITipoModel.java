package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;

/**
 *
 * @author olmaton
 */
public interface ITipoModel {
       
    public ArrayList<Tipo> listar(Usuario usuario) throws OlmException;
    public boolean eliminar(Tipo tipo) throws OlmException;
    public boolean agregarPorUsuario(Tipo tipo,Usuario usuario) throws OlmException;
    public boolean editarPorUsuario(Tipo tipo) throws OlmException;
}
