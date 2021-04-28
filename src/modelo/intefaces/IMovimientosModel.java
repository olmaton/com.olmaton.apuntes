package modelo.intefaces;

import presentacion.utiles.OlmException;
import entidades.Movimiento;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.ReporteMovimientoDTO;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public interface IMovimientosModel {
    public boolean guardar(Movimiento movimiento,Sesion sesion) throws OlmException;
    public boolean editar(Movimiento movimiento,Sesion sesion) throws OlmException;
    public boolean eliminar(Movimiento movimiento,Sesion sesion) throws OlmException; 
    public ArrayList<Movimiento> listar(Sesion sesion,FiltroMovimientoDTO filtro) throws OlmException;
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Sesion sesion,FiltroMovimientoDTO filtro) throws OlmException;
}
