package com.example.deeseecomics.jackson.serializer;

import com.example.deeseecomics.api.dto.Superpower;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SuperpowerSerializerTest {

    @Mock
    private JsonGenerator jsonGenerator;
    @InjectMocks
    private SuperpowerSerializer superpowerSerializer;

    @Test
    void testSerialize() throws IOException {
        superpowerSerializer.serialize(Superpower.FLIGHT, jsonGenerator, null);
        verify(jsonGenerator, times(1)).writeString(Superpower.FLIGHT.toString().toLowerCase());
    }
}
