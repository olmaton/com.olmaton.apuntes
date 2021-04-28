package modelo.api;

import presentacion.utiles.LocalDateAdapterSerializer;
import presentacion.utiles.LocalDateAdapterDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import configuracion.Configuracion;
import entidades.Usuario;
import java.time.LocalDateTime;
import modelo.dto.SesionDTO;
import presentacion.utiles.OlmException;
import servicios.Sesion;
import modelo.intefaces.IAuthModel;
import presentacion.utiles.OlmHttp;

/**
 *
 * @author olmaton
 */
public class AuthApiModel implements IAuthModel {

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterSerializer()).create();
    private final OlmHttp http;

    public AuthApiModel() {
        http = new OlmHttp(Configuracion.API_URL_BASE, Configuracion.APP_CODIGO);
    }

    @Override
    public SesionDTO login(Usuario usuario) throws OlmException {
        try {

            String request = gson.toJson(usuario);
            String httpResponse = http.post(ApiUrl.AUTH_LOGIN, request, "");
            if (!httpResponse.contains("token")) {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",login");
            }
            SesionDTO sesion = gson.fromJson(httpResponse, SesionDTO.class);
            return sesion;

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",login");
        }
    }

}
