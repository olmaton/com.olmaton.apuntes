package modelo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import configuracion.Configuracion;
import entidades.Tipo;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.intefaces.ITipoModel;
import presentacion.utiles.LocalDateAdapterDeserializer;
import presentacion.utiles.LocalDateAdapterSerializer;
import presentacion.utiles.OlmException;
import presentacion.utiles.OlmHttp;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public class TipoApiModel implements ITipoModel {

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterSerializer()).create();
    private final OlmHttp http;

    public TipoApiModel() {
        http = new OlmHttp(Configuracion.API_URL_BASE, Configuracion.APP_CODIGO);
    }

    @Override
    public ArrayList<Tipo> listar(Sesion sesion) throws OlmException {
        try {            
            String httpResponse = http.get(ApiUrl.TIPOS, null,sesion.getToken());
             if (!httpResponse.contains("[")||!httpResponse.contains("]")) {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",listar");
            }
            JsonArray jsonArray = gson.fromJson(httpResponse, JsonArray.class);
            Type listType = new TypeToken<ArrayList<Tipo>>() {}.getType();

            ArrayList<Tipo> retorno = gson.fromJson(jsonArray, listType);
            return retorno;

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",listar");
        }
    }

    @Override
    public boolean eliminar(Tipo tipo,Sesion sesion) throws OlmException {
                try {
            String httpResponse = http.delete(ApiUrl.TIPOS, tipo.obtenerParametrosUrl(), sesion.getToken());

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
    public boolean agregarPorUsuario(Tipo tipo, Sesion sesion) throws OlmException {
        try {

            String request = gson.toJson(tipo);
            String httpResponse = http.post(ApiUrl.TIPOS, request, sesion.getToken());

            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",agregarPorUsuario");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",agregarPorUsuario");
        }
    }

    @Override
    public boolean editarPorUsuario(Tipo tipo,Sesion sesion) throws OlmException {
        try {

            String request = gson.toJson(tipo);
            String httpResponse = http.put(ApiUrl.TIPOS, request, sesion.getToken());

            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",editarPorUsuario");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",editarPorUsuario");
        }
    }

}
