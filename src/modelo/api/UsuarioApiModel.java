/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import configuracion.Configuracion;
import entidades.Usuario;
import java.time.LocalDateTime;
import modelo.intefaces.IUsuarioModel;
import presentacion.utiles.LocalDateAdapterDeserializer;
import presentacion.utiles.LocalDateAdapterSerializer;
import presentacion.utiles.OlmException;
import presentacion.utiles.OlmHttp;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public class UsuarioApiModel implements IUsuarioModel {

    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterSerializer()).create();
    private final OlmHttp http;

    public UsuarioApiModel() {
        http = new OlmHttp(Configuracion.API_URL_BASE, Configuracion.APP_CODIGO);
    }

    @Override
    public boolean registrarme(Usuario usuario) throws OlmException {
        try {

            String request = gson.toJson(usuario);
            String httpResponse = http.post(ApiUrl.USUARIOS_REGISTRARME, request,"");

            if (httpResponse.contains("OK")) {
                return true;
            } else {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",registrarme");
            }

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",login");
        }
    }

    @Override
    public boolean existeEmail(String email) throws OlmException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
