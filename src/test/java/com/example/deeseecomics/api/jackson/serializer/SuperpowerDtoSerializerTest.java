package com.example.deeseecomics.api.jackson.serializer;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.api.jackson.serializer.SuperpowerDtoSerializer;
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
public class SuperpowerDtoSerializerTest {

    @Mock
    private JsonGenerator jsonGenerator;
    @InjectMocks
    private SuperpowerDtoSerializer superpowerDTOSerializer;

    @Test
    void testSerialize() throws IOException {
        superpowerDTOSerializer.serialize(SuperpowerDTO.FLIGHT, jsonGenerator, null);
        verify(jsonGenerator, times(1)).writeString(SuperpowerDTO.FLIGHT.toString().toLowerCase());
    }
}
