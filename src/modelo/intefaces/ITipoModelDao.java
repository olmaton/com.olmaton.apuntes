package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;

/**
 *
 * @author olmaton
 */
public interface ITipoModelDao {
       
    
    public ArrayList<Tipo> listar(Usuario usuario) throws OlmException;
    public boolean eliminar(Tipo tipo) throws OlmException;
    public boolean agregarPorUsuario(Tipo tipo,Usuario usuario) throws OlmException;
    public boolean editarPorUsuario(Tipo tipo) throws OlmException;
    
    
    public Tipo getPorId(int id) throws OlmException; 
    public boolean guardar(Tipo tipo) throws OlmException;
    public boolean guardarTiposUsuarios(Tipo tipo,Usuario usuario) throws OlmException;
    public ArrayList<Tipo> listarGenerales() throws OlmException;
}
