package modelo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import configuracion.Configuracion;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.dto.FiltroMovimientoDTO;
import modelo.dto.ReporteMovimientoDTO;
import modelo.intefaces.IMovimientosModel;
import presentacion.utiles.LocalDateAdapterDeserializer;
import presentacion.utiles.LocalDateAdapterSerializer;
import presentacion.utiles.OlmException;
import presentacion.utiles.OlmHttp;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public class MovimientoApiModel implements IMovimientosModel {

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterSerializer()).create();
    private final OlmHttp http;

    public MovimientoApiModel() {
        http = new OlmHttp(Configuracion.API_URL_BASE, Configuracion.APP_CODIGO);
    }

    @Override
    public boolean guardar(Movimiento movimiento,Sesion sesion) throws OlmException {
        try {

            String request = gson.toJson(movimiento);
            String httpResponse = http.post(ApiUrl.MOVIMIENTOS, request,sesion.getToken());
            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",guardar");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",guardar");
        }
    }

    @Override
    public boolean editar(Movimiento movimiento,Sesion sesion) throws OlmException {
         try {

            String request = gson.toJson(movimiento);
            String httpResponse = http.put(ApiUrl.MOVIMIENTOS, request,sesion.getToken());
            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",editar");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",editar");
        }
    }

    @Override
    public boolean eliminar(Movimiento movimiento,Sesion sesion) throws OlmException {
         try {
            String httpResponse = http.delete(ApiUrl.MOVIMIENTOS, movimiento.obtenerParametrosUrl(), sesion.getToken());

            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",eliminar");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",eliminar");
        }
    }

    @Override
    public ArrayList<Movimiento> listar(Sesion sesion, FiltroMovimientoDTO filtro) throws OlmException {
        try {
            String httpResponse = http.get(ApiUrl.MOVIMIENTOS_FILTRAR, filtro.obtenerParametrosUrl(), sesion.getToken());
            if (!httpResponse.contains("[") || !httpResponse.contains("]")) {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",listar");
            }
            JsonArray jsonArray = gson.fromJson(httpResponse, JsonArray.class);
            Type listType = new TypeToken<ArrayList<Movimiento>>() {
            }.getType();

            ArrayList<Movimiento> retorno = gson.fromJson(jsonArray, listType);
            return retorno;

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",listar");
        }
    }

    @Override
    public ArrayList<ReporteMovimientoDTO> reporteConsulta(Sesion sesion, FiltroMovimientoDTO filtro) throws OlmException {
        try {
            String httpResponse = http.get(ApiUrl.MOVIMIENTOS_REPORTE_FILTRAR, filtro.obtenerParametrosUrl(), sesion.getToken());
            if (!httpResponse.contains("[") || !httpResponse.contains("]")) {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",listar");
            }
            JsonArray jsonArray = gson.fromJson(httpResponse, JsonArray.class);
            if (jsonArray.size() > 0) {
                Type listType = new TypeToken<ArrayList<ReporteMovimientoDTO>>() {
                }.getType();

                ArrayList<ReporteMovimientoDTO> retorno = gson.fromJson(jsonArray, listType);
                return retorno;
            }

            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",reporteConsulta");
        }
    }

}
