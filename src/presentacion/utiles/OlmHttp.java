package presentacion.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author olmaton
 */
public class OlmHttp {
    private final String api,app_codigo;
    
    public OlmHttp(String api,String app_codigo) {
        this.api = api;
        this.app_codigo = app_codigo;
    }    
   

    private String obtenerParametrosUrl(ArrayList<String[]> payload) {
        StringBuilder retorno = new StringBuilder();
        if (payload != null) {
            for (String[] prm : payload) {
                retorno.append(prm[0]);
                retorno.append("=");
                retorno.append(prm[1]);
                retorno.append("&");
            }
        }

        return retorno.toString();
    }

    public String get(String prmUrl, ArrayList<String[]> payload,String token) throws OlmException {
        try {
            URL url = new URL(api+prmUrl+"?"+obtenerParametrosUrl(payload));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");        
            connection.setRequestProperty("User-Agent", app_codigo);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); 
            connection.setRequestProperty("Authorization", token);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();            
            imprimirConsola("get",prmUrl,obtenerParametrosUrl(payload),jsonString.toString());
            return jsonString.toString();
        } catch (IOException e) {            
            throw new OlmException(e.getMessage(),3,this.getClass().getName() + ".get");
        }
    }
   
    public String post(String prmUrl, String payload,String token) throws OlmException {
        try {
            URL url = new URL(api+prmUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           
            
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", app_codigo);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", token);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            imprimirConsola("post",prmUrl,payload,jsonString.toString());
            return jsonString.toString();
        } catch (IOException e) {            
            throw new OlmException(e.getMessage(),3,this.getClass().getName() + ".post");
        }
    }
    
    public String put(String prmUrl, String payload,String token) throws OlmException {
        try {
            URL url = new URL(api+prmUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("User-Agent", app_codigo);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", token);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            imprimirConsola("put",prmUrl,payload,jsonString.toString());
            return jsonString.toString();
        } catch (IOException e) {
            throw new OlmException(e.getMessage(),3,this.getClass().getName() + ".put");
        }
    }
    
    
    
    public String delete(String prmUrl, ArrayList<String[]> payload,String token) throws OlmException {
        try {
            URL url = new URL(api+prmUrl+"?"+obtenerParametrosUrl(payload));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("User-Agent", app_codigo);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", token);           
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            imprimirConsola("delete",prmUrl,obtenerParametrosUrl(payload),jsonString.toString());
            return jsonString.toString();
        } catch (IOException e) {
            throw new OlmException(e.getMessage(),3,this.getClass().getName() + ".delete");
        }
    }
    
    private void imprimirConsola(String method,String url,String payload,String response){
        System.out.println(method+"|"+url+"|"+payload+"|"+response);
    }
}
