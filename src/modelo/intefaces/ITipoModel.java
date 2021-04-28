package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public interface ITipoModel {
       
    public ArrayList<Tipo> listar(Sesion sesion) throws OlmException;
    public boolean eliminar(Tipo tipo,Sesion sesion) throws OlmException;
    public boolean agregarPorUsuario(Tipo tipo,Sesion sesion) throws OlmException;
    public boolean editarPorUsuario(Tipo tipo,Sesion sesion) throws OlmException;
}
