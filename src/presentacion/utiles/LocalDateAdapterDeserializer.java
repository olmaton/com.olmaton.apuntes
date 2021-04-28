package presentacion.utiles;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author olmaton
 */
public class LocalDateAdapterDeserializer implements JsonDeserializer<LocalDateTime> {

    DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd HH:mm:ss").toFormatter();

    public LocalDateAdapterDeserializer() {

    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {

        if (json.toString().contains("T")) {
            dtf = DateTimeFormatter.ISO_DATE_TIME;
        }
        LocalDateTime time = LocalDateTime.parse(json.getAsString(), dtf);
        return time.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneOffset.systemDefault())
                .toLocalDateTime();

    }

}
