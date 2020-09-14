package ai.brace.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LastModifiedSerializer implements JsonSerializer<LastModified> {

    @Override
    public JsonElement serialize(LastModified src, Type typeOfSrc, JsonSerializationContext context) {
        long epoch = src.getLastModified();
        LocalDateTime ldt = Instant.ofEpochSecond(epoch).atZone(ZoneId.of("UTC")).toLocalDateTime();
        return new JsonPrimitive(ldt.toString());
    }
}
