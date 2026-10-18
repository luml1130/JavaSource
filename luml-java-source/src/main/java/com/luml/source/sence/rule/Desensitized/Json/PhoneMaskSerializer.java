package com.luml.source.sence.rule.Desensitized.Json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PhoneMaskSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        /*if (s == null || s.length() != 11) {
            jsonGenerator.writeString(s);
            return;
        }
        String masked = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        jsonGenerator.writeString(masked);*/

        if (value != null && value.length() == 11) {
            jsonGenerator.writeString(value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1&zwnj;****&zwnj;$2"));
        } else {
            jsonGenerator.writeString(value);
        }
    }
}
