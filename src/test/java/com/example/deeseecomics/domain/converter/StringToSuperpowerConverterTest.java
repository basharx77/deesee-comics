package com.example.deeseecomics.domain.converter;

import com.example.deeseecomics.domain.model.Superpower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringToSuperpowerConverterTest {

    private final StringToSuperpowerConverter converter = new StringToSuperpowerConverter();

    @Test
    void shouldConvertStringToSuperpower() {
        assertEquals(Superpower.FLIGHT, converter.convert(Superpower.FLIGHT.toString().toLowerCase()));
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convert("UNKNOWN"));
    }
}
