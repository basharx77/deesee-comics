package com.example.deeseecomics.api.converter;

import com.example.deeseecomics.api.converter.StringToSuperpowerConverter;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringToSuperpowerConverterTest {

    private final StringToSuperpowerConverter converter = new StringToSuperpowerConverter();

    @Test
    void shouldConvertStringToSuperpower() {
        assertEquals(SuperpowerDTO.FLIGHT, converter.convert(SuperpowerDTO.FLIGHT.toString().toLowerCase()));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convert("UNKNOWN"));
    }
}
