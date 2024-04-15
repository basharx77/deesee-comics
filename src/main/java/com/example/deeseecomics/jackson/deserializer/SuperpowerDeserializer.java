package com.example.deeseecomics.jackson.deserializer;

import com.example.deeseecomics.api.dto.Superpower;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class SuperpowerDeserializer extends JsonDeserializer<Superpower> {

    @Override
    public Superpower deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        return Superpower.valueOf(jsonParser.getText().toUpperCase());
    }
}