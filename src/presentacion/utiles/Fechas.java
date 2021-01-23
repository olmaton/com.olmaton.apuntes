package presentacion.utiles;

import configuracion.Configuracion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author olmaton
 */
public class Fechas {
  
    private static final DateTimeFormatter DTF_FECHA_HORA = DateTimeFormatter.ofPattern(Configuracion.FORMATO_FECHA_HORA);
    private static final DateTimeFormatter DTF_FECHA_HORA_CORTA = DateTimeFormatter.ofPattern(Configuracion.FORMATO_FECHA_HORA_CORTA);
        
    public static String setFechaHoraFromLocalDateTime(LocalDateTime date){
        return date.format(DTF_FECHA_HORA);   
    }
    
    public static String setFechaHoraCortaFromLocalDateTime(LocalDateTime date){
        return date.format(DTF_FECHA_HORA_CORTA);   
    }
}
