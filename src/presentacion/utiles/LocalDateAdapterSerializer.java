package presentacion.utiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author olmaton
 */
public class LocalDateAdapterSerializer implements JsonSerializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {        
        return new JsonPrimitive(date.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)
                                       .toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

}
