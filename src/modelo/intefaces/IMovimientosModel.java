package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.ReporteMovimientoDTO;

/**
 *
 * @author olmaton
 */
public interface IMovimientosModel {
    public boolean guardar(Movimiento movimiento) throws OlmException;
    public boolean editar(Movimiento movimiento) throws OlmException;
    public boolean eliminar(Movimiento movimiento) throws OlmException; 
    public ArrayList<Movimiento> listar(Usuario usuario,FiltroMovimientoDTO filtro) throws OlmException;
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Usuario usuario,FiltroMovimientoDTO filtro) throws OlmException;
}
