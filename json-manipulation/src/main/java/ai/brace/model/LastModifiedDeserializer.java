package ai.brace.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LastModifiedDeserializer implements JsonDeserializer<LastModified> {
    @Override
    public LastModified deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        LastModified lm = new LastModified();
        lm.setLastModified(Long.parseLong(json.getAsString()));
        return lm;
    }
}
