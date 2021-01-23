package presentacion.utiles;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Clase personalizada para logs
 * @author olmaton
 */
public class OlmLog {
    private static OlmLog instancia;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    private final OlmFile error;
    private final OlmFile info;
    public OlmLog() {
        this.error = new OlmFile("apuntes_error.olmlog");
        this.info = new OlmFile("apuntes_info.olmlog");
    }   
    public static OlmLog getInstancia()
    {
        if(instancia==null){
            instancia= new OlmLog();
        }        
        return instancia;
    }
    
    public void error(String string,String origen){
        System.out.println("error|"+LocalDateTime.now().format(dtf)+"|"+origen+"|"+string);
        error.setLinea("error|"+LocalDateTime.now().format(dtf)+"|"+origen+"|"+string);
    }
    
    public void info(String string,String origen){
        System.out.println("info|"+LocalDateTime.now().format(dtf)+"|"+origen+"|"+string);
        info.setLinea("info|"+LocalDateTime.now().format(dtf)+"|"+origen+"|"+string);        
    }
}
