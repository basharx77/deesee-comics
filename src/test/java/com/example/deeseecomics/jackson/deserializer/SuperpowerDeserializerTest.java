package com.example.deeseecomics.jackson.deserializer;

import com.example.deeseecomics.api.dto.Superpower;
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
public class SuperpowerDeserializerTest {

    @Mock
    private JsonParser jsonParser;
    @InjectMocks
    private SuperpowerDeserializer superpowerDeserializer;


    @Test
    void testDeserialize() throws IOException {
        when(jsonParser.getText()).thenReturn(Superpower.FLIGHT.toString().toLowerCase());
        assertEquals(Superpower.FLIGHT, superpowerDeserializer.deserialize(jsonParser, null));
    }
}
