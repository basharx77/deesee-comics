package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class Dto2DomainMapperTest {
    @Test
    public void shouldMapDomainSuperpowerToDto() {
        assertEquals(Dto2DomainMapper.mapSuperpowerDtoToDomain(SuperpowerDTO.FLIGHT.FLIGHT),
                Superpower.FLIGHT);
    }

    @Test
    public void shouldMapDomainSuperpowersToDtos() {
        var superpowers = Dto2DomainMapper.mapSuperpowersDtoToDomain(EnumSet.allOf(SuperpowerDTO.class));
        assertEquals(superpowers, EnumSet.allOf(Superpower.class));
    }
}