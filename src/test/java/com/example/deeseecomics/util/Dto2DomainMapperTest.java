package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superpower;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class Dto2DomainMapperTest {
    @Test
    public void shouldMapModelSuperpowerToDto() {
        assertEquals(Dto2ModelMapper.mapSuperpowerDtoToModel(SuperpowerDTO.FLIGHT.FLIGHT),
                Superpower.FLIGHT);
    }

    @Test
    public void shouldMapModelSuperpowersToDtos() {
        var superpowers = Dto2ModelMapper.mapSuperpowersDtoToModel(EnumSet.allOf(SuperpowerDTO.class));
        assertEquals(superpowers, EnumSet.allOf(Superpower.class));
    }
}