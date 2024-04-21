package com.example.deeseecomics.api.jackson.serializer;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class SuperpowerDtoSerializer extends JsonSerializer<SuperpowerDTO> {

    @Override
    public void serialize(SuperpowerDTO value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.name().toLowerCase());
    }
}