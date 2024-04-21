package com.example.deeseecomics.api.jackson.deserializer;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class SuperpowerDtoDeserializer extends JsonDeserializer<SuperpowerDTO> {

    @Override
    public SuperpowerDTO deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        return SuperpowerDTO.valueOf(jsonParser.getText().toUpperCase());
    }
}