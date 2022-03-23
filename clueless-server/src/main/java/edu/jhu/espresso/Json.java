package edu.jhu.espresso;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        // any configurations here
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String str) throws JsonProcessingException {
        return objectMapper.readTree(str);
    }

    public static <A> A fromJson(JsonNode node, Class<A> someClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, someClass);
    }


    public static JsonNode toJson(Object anObject) {
        return objectMapper.valueToTree(anObject);
    }

    public static String jsonString(JsonNode node, boolean indent) throws JsonProcessingException {

        ObjectWriter objectWriter = objectMapper.writer();

        if (indent){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }

        return objectWriter.writeValueAsString(node);

    }

    public static byte[] jsonByte(JsonNode node, boolean indent) throws JsonProcessingException {

        ObjectWriter objectWriter = objectMapper.writer();

        if (indent){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }

        return objectWriter.writeValueAsBytes(node);
    }

}
