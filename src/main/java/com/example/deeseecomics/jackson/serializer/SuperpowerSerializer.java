package com.example.deeseecomics.jackson.serializer;

import com.example.deeseecomics.api.dto.Superpower;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class SuperpowerSerializer extends JsonSerializer<Superpower> {

    @Override
    public void serialize(Superpower value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.name().toLowerCase());
    }
}