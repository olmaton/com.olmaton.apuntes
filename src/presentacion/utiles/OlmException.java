package presentacion.utiles;

/**
 * Clase personalizada para manejo de excepciones
 * @author olmaton
 */
public class OlmException extends Exception{
    private int code = -1;
    
    public OlmException(String message, int code,String origen) {
        super(message);
        this.code = code;
        OlmLog.getInstancia().error(message,origen);
    }
    
    public int getCode() {
        return code;
    }
}
