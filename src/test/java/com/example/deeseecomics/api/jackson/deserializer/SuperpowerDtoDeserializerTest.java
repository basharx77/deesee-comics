package com.example.deeseecomics.api.jackson.deserializer;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.api.jackson.deserializer.SuperpowerDtoDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperpowerDtoDeserializerTest {

    @Mock
    private JsonParser jsonParser;
    @InjectMocks
    private SuperpowerDtoDeserializer superpowerDTODeserializer;


    @Test
    void testDeserialize() throws IOException {
        when(jsonParser.getText()).thenReturn(SuperpowerDTO.FLIGHT.toString().toLowerCase());
        assertEquals(SuperpowerDTO.FLIGHT, superpowerDTODeserializer.deserialize(jsonParser, null));
    }
}
