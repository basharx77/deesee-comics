package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superpower;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Dto2DomainMapperTest {
    @Test
    public void shouldMapSuperpowerModelToDto() {
        assertEquals(Dto2ModelMapper.mapSuperpowerDtoToModel(SuperpowerDTO.FLIGHT),
                Superpower.FLIGHT);
    }

    @Test
    public void shouldMapSuperpowerModelsToDtos() {
        var superpowers = Dto2ModelMapper.mapSuperpowerDtosToModels(EnumSet.allOf(SuperpowerDTO.class));
        assertEquals(superpowers, EnumSet.allOf(Superpower.class));
    }
}