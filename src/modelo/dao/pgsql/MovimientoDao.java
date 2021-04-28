package modelo.dao.pgsql;

import presentacion.utiles.OlmException;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.ReporteMovimientoDTO;
import modelo.intefaces.IMovimientosModelDao;

/**
 *
 * @author olmaton
 */
public class MovimientoDao implements IMovimientosModelDao {

    private static final String INSERT = "insert into movimientos(id_cuenta,nombre,descripcion,id_tipo,fecha_hora,valor_unitario_ingreso,valor_unitario_egreso,cantidad,es_presupuesto) "
            + "values(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "update movimientos set id_cuenta=?,nombre=?,descripcion=?,id_tipo=?,fecha_hora=?,valor_unitario_ingreso=?,valor_unitario_egreso=?,cantidad=?,es_presupuesto=?,actualizado=current_timestamp "
            + "where id=?";
    private static final String FILTRO_LISTAR = "select * " 
                    + " from movimientos where activo=1 and (nombre||descripcion) like ? "
                    + "and (id_cuenta=? or (?=0 and id_cuenta in (select id from cuentas where id_usuario=? and activo=1))) "
                    + "and (id_tipo=? or 0=?) "
                    + "and (es_presupuesto=? or -1=?) ";
    private static final String FILTRO_REPORTE = "select es_presupuesto,sum(valor_unitario_egreso*cantidad) as total_egresos,sum(valor_unitario_ingreso*cantidad) as total_ingresos,count(*) AS cantidad_total"
                    + " from movimientos where activo=1 and (nombre||descripcion) like ? "
                    + "and (id_cuenta=? or (?=0 and id_cuenta in (select id from cuentas where id_usuario=? and activo=1))) "
                    + "and (id_tipo=? or 0=?) "
                    + "and (es_presupuesto=? or -1=?) ";

    TipoDao tipoDao = new TipoDao();
    CuentaDao cuentaDao = new CuentaDao();
    public MovimientoDao() {
    }
    
    
    
    @Override
    public boolean guardar(Movimiento movimiento) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, movimiento.getCuenta().getId());
            ps.setString(2, movimiento.getNombre());
            ps.setString(3, movimiento.getDescripcion());
            ps.setInt(4, movimiento.getTipo().getId());
            ps.setObject(5, movimiento.getFecha_hora());
            ps.setDouble(6, movimiento.getValor_unitario_ingreso());
            ps.setDouble(7, movimiento.getValor_unitario_egreso());
            ps.setInt(8, movimiento.getCantidad());
            ps.setInt(9, movimiento.getEs_presupuesto());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                movimiento.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".guardar");
        }
        return false;
    }

    @Override
    public boolean editar(Movimiento movimiento) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, movimiento.getCuenta().getId());
            ps.setString(2, movimiento.getNombre());
            ps.setString(3, movimiento.getDescripcion());
            ps.setInt(4, movimiento.getTipo().getId());
            ps.setObject(5, movimiento.getFecha_hora());
            ps.setDouble(6, movimiento.getValor_unitario_ingreso());
            ps.setDouble(7, movimiento.getValor_unitario_egreso());
            ps.setInt(8, movimiento.getCantidad());
            ps.setInt(9, movimiento.getEs_presupuesto());
            ps.setInt(10, movimiento.getId());
            ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".editar");
        }
    }

    @Override
    public boolean eliminar(Movimiento movimiento) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("update movimientos set activo=0,actualizado=current_timestamp where id=?");
            ps.setInt(1, movimiento.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".eliminar");
        }
    }

    @Override
    public ArrayList<Movimiento> listar(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        ArrayList<Movimiento> retorno = new ArrayList<>();
        
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareCall(FILTRO_LISTAR
                    + " order by creado desc limit ? offset ?;");
            ps.setString(1, "%" + filtro.getBuscar() + "%");
            ps.setInt(2, filtro.getCuenta().getId());
            ps.setInt(3, filtro.getCuenta().getId());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, filtro.getTipo().getId());
            ps.setInt(6, filtro.getTipo().getId());
            ps.setInt(7, filtro.getMovimientoOPresupueso());
            ps.setInt(8, filtro.getMovimientoOPresupueso());
            ps.setInt(9, filtro.getLimit());
            ps.setInt(10, filtro.getOffset());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setId(rs.getInt("id"));
                movimiento.setNombre(rs.getString("nombre"));
                movimiento.setDescripcion(rs.getString("descripcion"));
                movimiento.setFecha_hora(rs.getObject("fecha_hora", LocalDateTime.class));
                movimiento.setTipo(tipoDao.getPorId(rs.getInt("id_tipo")));
                movimiento.setCuenta(cuentaDao.getPorId(rs.getInt("id_cuenta")));
                movimiento.setValor_unitario_ingreso(rs.getDouble("Valor_unitario_ingreso"));
                movimiento.setValor_unitario_egreso(rs.getDouble("Valor_unitario_egreso"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setCreado(rs.getObject("creado", LocalDateTime.class));
                movimiento.setActualizado(rs.getObject("actualizado", LocalDateTime.class));
                movimiento.setActivo(rs.getInt("activo"));
                movimiento.setEs_presupuesto(rs.getInt("Es_presupuesto"));

                retorno.add(movimiento);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listar");
        }
        return retorno;
    }
    
    @Override
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        ArrayList<ReporteMovimientoDTO> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareCall(FILTRO_REPORTE
                    
                    + "group by es_presupuesto");
            ps.setString(1, "%" + filtro.getBuscar() + "%");
            ps.setInt(2, filtro.getCuenta().getId());
            ps.setInt(3, filtro.getCuenta().getId());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, filtro.getTipo().getId());
            ps.setInt(6, filtro.getTipo().getId());
            ps.setInt(7, filtro.getMovimientoOPresupueso());
            ps.setInt(8, filtro.getMovimientoOPresupueso());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReporteMovimientoDTO servicio = new ReporteMovimientoDTO();
                servicio.setCantidad_total(rs.getInt("Cantidad_total"));
                servicio.setEs_presupuesto(rs.getInt("Es_presupuesto"));
                servicio.setTotal_egresos(rs.getDouble("Total_egresos"));
                servicio.setTotal_ingresos(rs.getDouble("Total_ingresos"));

                retorno.add(servicio);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listar");
        }
        return retorno;
    }

    @Override
    public ArrayList<Movimiento> listarPorFechas(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        ArrayList<Movimiento> retorno = new ArrayList<>();
        CuentaDao cuentaDao = new CuentaDao();
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareCall(FILTRO_LISTAR
                    +" and fecha_hora>=? and fecha_hora<=? "
                    + " order by creado desc limit ? offset ?;");
            ps.setString(1, "%" + filtro.getBuscar() + "%");
            ps.setInt(2, filtro.getCuenta().getId());
            ps.setInt(3, filtro.getCuenta().getId());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, filtro.getTipo().getId());
            ps.setInt(6, filtro.getTipo().getId());
            ps.setInt(7, filtro.getMovimientoOPresupueso());
            ps.setInt(8, filtro.getMovimientoOPresupueso());
            ps.setObject(9, filtro.getFechaHoraInicio());
            ps.setObject(10, filtro.getFechaHoraFin());            
            ps.setInt(11, filtro.getLimit());
            ps.setInt(12, filtro.getOffset());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setId(rs.getInt("id"));
                movimiento.setNombre(rs.getString("nombre"));
                movimiento.setDescripcion(rs.getString("descripcion"));
                movimiento.setFecha_hora(rs.getObject("fecha_hora", LocalDateTime.class));
                movimiento.setTipo(tipoDao.getPorId(rs.getInt("id_tipo")));
                movimiento.setCuenta(cuentaDao.getPorId(rs.getInt("id_cuenta")));
                movimiento.setValor_unitario_ingreso(rs.getDouble("Valor_unitario_ingreso"));
                movimiento.setValor_unitario_egreso(rs.getDouble("Valor_unitario_egreso"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setCreado(rs.getObject("creado", LocalDateTime.class));
                movimiento.setActualizado(rs.getObject("actualizado", LocalDateTime.class));
                movimiento.setActivo(rs.getInt("activo"));
                movimiento.setEs_presupuesto(rs.getInt("Es_presupuesto"));

                retorno.add(movimiento);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listarPorFechas");
        }
        return retorno;
    }

    @Override
    public ArrayList<ReporteMovimientoDTO> reporteConsultaPorFechas(Usuario usuario, FiltroMovimientoDTO filtro) throws OlmException {
        ArrayList<ReporteMovimientoDTO> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareCall(FILTRO_REPORTE
                    +" and fecha_hora>=? and fecha_hora<=? "
                    + "group by es_presupuesto");
            ps.setString(1, "%" + filtro.getBuscar() + "%");
            ps.setInt(2, filtro.getCuenta().getId());
            ps.setInt(3, filtro.getCuenta().getId());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, filtro.getTipo().getId());
            ps.setInt(6, filtro.getTipo().getId());
            ps.setInt(7, filtro.getMovimientoOPresupueso());
            ps.setInt(8, filtro.getMovimientoOPresupueso());
            ps.setObject(9, filtro.getFechaHoraInicio());
            ps.setObject(10, filtro.getFechaHoraFin());       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReporteMovimientoDTO servicio = new ReporteMovimientoDTO();
                servicio.setCantidad_total(rs.getInt("Cantidad_total"));
                servicio.setEs_presupuesto(rs.getInt("Es_presupuesto"));
                servicio.setTotal_egresos(rs.getDouble("Total_egresos"));
                servicio.setTotal_ingresos(rs.getDouble("Total_ingresos"));

                retorno.add(servicio);
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ".listar");
        }
        return retorno;
    }
    
    

}
