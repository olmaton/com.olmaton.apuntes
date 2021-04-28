/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import configuracion.Configuracion;
import presentacion.utiles.OlmException;
import entidades.Cuenta;
import entidades.Tipo;
import entidades.Usuario;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.intefaces.ICuentaModel;
import presentacion.utiles.LocalDateAdapterDeserializer;
import presentacion.utiles.LocalDateAdapterSerializer;
import presentacion.utiles.OlmHttp;
import servicios.Sesion;

/**
 *
 * @author olmaton
 */
public class CuentaApiModel implements ICuentaModel {

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapterSerializer()).create();
    private final OlmHttp http;

    public CuentaApiModel() {
        http = new OlmHttp(Configuracion.API_URL_BASE, Configuracion.APP_CODIGO);
    }

    @Override
    public boolean guardar(Cuenta cuenta, Sesion sesion) throws OlmException {
        try {

            String request = gson.toJson(cuenta);
            String httpResponse = http.post(ApiUrl.CUENTAS, request, sesion.getToken());

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
    public boolean editar(Cuenta cuenta, Sesion sesion) throws OlmException {
        try {

            String request = gson.toJson(cuenta);
            String httpResponse = http.put(ApiUrl.CUENTAS, request, sesion.getToken());

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
    public boolean eliminar(Cuenta cuenta, Sesion sesion) throws OlmException {
        try {
            String httpResponse = http.delete(ApiUrl.CUENTAS, cuenta.obtenerParametrosUrl(), sesion.getToken());

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
    public ArrayList<Cuenta> listar(Sesion sesion) throws OlmException {
        try {
            String httpResponse = http.get(ApiUrl.CUENTAS, null, sesion.getToken());
            if (!httpResponse.contains("[") || !httpResponse.contains("]")) {
                throw new OlmException(httpResponse, 2, this.getClass().getName() + ",listar");
            }

            JsonArray jsonArray = gson.fromJson(httpResponse, JsonArray.class);
            Type listType = new TypeToken<ArrayList<Cuenta>>() {
            }.getType();

            ArrayList<Cuenta> retorno = gson.fromJson(jsonArray, listType);
            return retorno;

        } catch (JsonSyntaxException e) {
            throw new OlmException(e.getMessage(), 3, this.getClass().getName() + ",listar");
        }
    }

    @Override
    public Cuenta getPorId(int id, Sesion sesion) throws OlmException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
