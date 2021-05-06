package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.MovimientoCuentaACuentaDTO;
import modelo.dto.ReporteMovimientoDTO;
import modelo.intefaces.IMovimientosModelDao;
import modelo.intefaces.IMovimientosModel;
import servicios.Sesion;

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
    public boolean guardar(Movimiento movimiento,Sesion sesion) throws OlmException {
        return dao.guardar(movimiento);
    }

    @Override
    public boolean editar(Movimiento movimiento,Sesion sesion) throws OlmException {
        return dao.editar(movimiento);
    }

    @Override
    public boolean eliminar(Movimiento movimiento,Sesion sesion) throws OlmException {
        return dao.eliminar(movimiento);
    }


    @Override
    public ArrayList<Movimiento> listar(Sesion sesion, FiltroMovimientoDTO filtro) throws OlmException {
        if (filtro.getFechaHoraFin() != null && filtro.getFechaHoraInicio() != null) {
            return dao.listarPorFechas(sesion.getUsuario(), filtro);
        } else {
            return dao.listar(sesion.getUsuario(), filtro);
        }

    }

    @Override
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Sesion sesion, FiltroMovimientoDTO filtro) throws OlmException {
        if (filtro.getFechaHoraFin() != null && filtro.getFechaHoraInicio() != null) {
            return dao.reporteConsultaPorFechas(sesion.getUsuario(), filtro);
        } else {
            return dao.reporteConsulta(sesion.getUsuario(), filtro);
        }
    }

    @Override
    public boolean moverEntreCuentas(MovimientoCuentaACuentaDTO dto, Sesion sesion) throws OlmException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
