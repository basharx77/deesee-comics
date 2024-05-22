package com.example.deeseecomics.data.loader;

import com.example.deeseecomics.model.Superhero;
import com.example.deeseecomics.model.Superpower;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class SuperheroDataLoader {

    private final Resource resource;

    private ObjectMapper objectMapper;

    public SuperheroDataLoader(@Value("${datasource.path}") Resource resource) {
        this.resource = resource;
    }

    private ObjectMapper getObjectMapper() {

        if (objectMapper != null) {
            return objectMapper;
        }

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        SimpleModule module = new SimpleModule();
        module.addSerializer(Superpower.class, new SuperpowerSerializer());
        module.addDeserializer(Superpower.class, new SuperpowerDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }

    public List<Superhero> loadSuperheroes() {
        try (InputStream inputStream = resource.getInputStream()) {
            return getObjectMapper().readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SuperpowerDeserializer extends JsonDeserializer<Superpower> {
        @Override
        public Superpower deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
            return Superpower.valueOf(jsonParser.getText().toUpperCase());
        }
    }

    private static class SuperpowerSerializer extends JsonSerializer<Superpower> {
        @Override
        public void serialize(Superpower value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.name().toLowerCase());
        }
    }
}
