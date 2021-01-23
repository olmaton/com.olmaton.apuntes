package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.ReporteMovimientoDTO;
import modelo.intefaces.IMovimientosModelDao;
import modelo.intefaces.IMovimientosModel;

/**
 *
 * @author olmaton
 */
public class MovimientoModel implements IMovimientosModel {

    IMovimientosModelDao dao;

    public MovimientoModel() {
        switch (Configuracion.ORIGEN_DATOS_BD) {
            case "mysql": {
                dao = new modelo.dao.mysql.MovimientoDao();
                break;
            }
            case "pgsql": {
                dao = new modelo.dao.pgsql.MovimientoDao();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public boolean guardar(Movimiento movimiento) throws OlmException {
        return dao.guardar(movimiento);
    }

    @Override
    public boolean editar(Movimiento movimiento) throws OlmException {
        return dao.editar(movimiento);
    }

    @Override
    public boolean eliminar(Movimiento movimiento) throws OlmException {
        return dao.eliminar(movimiento);
    }


    @Override
    public ArrayList<Movimiento> listar(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        if (filtro.getFechaHoraFin() != null && filtro.getFechaHoraInicio() != null) {
            return dao.listarPorFechas(usuario, filtro);
        } else {
            return dao.listar(usuario, filtro);
        }

    }

    @Override
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        if (filtro.getFechaHoraFin() != null && filtro.getFechaHoraInicio() != null) {
            return dao.reporteConsultaPorFechas(usuario, filtro);
        } else {
            return dao.reporteConsulta(usuario, filtro);
        }
    }
}
