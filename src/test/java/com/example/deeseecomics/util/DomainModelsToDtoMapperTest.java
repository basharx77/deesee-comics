package com.example.deeseecomics.util;

import com.example.deeseecomics.TestDataSupport;
import com.example.deeseecomics.domain.model.Superpower;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static com.example.deeseecomics.TestAssertionHelper.assertSuperheroDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainModelsToDtoMapperTest extends TestDataSupport {


    @Test
    public void shouldMapDomainSuperheroToDto() {
        var superhero = DomainModelsToDtoMapper.mapDomainSuperheroToDto(FIRST_TEST_SUPERHERO);

        assertSuperheroDto(superhero,
                FIRST_TEST_SUPERHERO_NAME,
                FIRST_TEST_SUPERHERO_BIRTHDAY,
                DomainModelsToDtoMapper.mapDomainSuperpowersToDtos(FIRST_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(FIRST_TEST_SUPERHERO_FIRST_NAME,
                        FIRST_TEST_SUPERHERO_SECOND_NAME));
    }

    @Test
    public void shouldMapDomainSuperheroesToDtos() {
        var superheroes = DomainModelsToDtoMapper.mapDomainSuperheroesToDtos(List.of(FIRST_TEST_SUPERHERO,
                SECOND_TEST_SUPERHERO));


        assertSuperheroDto(superheroes.get(0),
                FIRST_TEST_SUPERHERO_NAME,
                FIRST_TEST_SUPERHERO_BIRTHDAY,
                DomainModelsToDtoMapper.mapDomainSuperpowersToDtos(FIRST_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(FIRST_TEST_SUPERHERO_FIRST_NAME,
                        FIRST_TEST_SUPERHERO_SECOND_NAME));

        assertSuperheroDto(superheroes.get(1),
                SECOND_TEST_SUPERHERO_NAME,
                SECOND_TEST_SUPERHERO_BIRTHDAY,
                DomainModelsToDtoMapper.mapDomainSuperpowersToDtos(SECOND_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(SECOND_TEST_SUPERHERO_FIRST_NAME,
                        SECOND_TEST_SUPERHERO_SECOND_NAME));

    }

    @Test
    public void shouldMapDomainSuperpowerToDto() {
        assertEquals(DomainModelsToDtoMapper.mapDomainSuperpowerToDto(Superpower.FLIGHT),
                com.example.deeseecomics.api.dto.Superpower.FLIGHT);
    }

    @Test
    public void shouldMapDomainSuperpowersToDtos() {
        var superpowerDtos = DomainModelsToDtoMapper.mapDomainSuperpowersToDtos(EnumSet.allOf(Superpower.class));
        assertEquals(superpowerDtos, EnumSet.allOf(com.example.deeseecomics.api.dto.Superpower.class));
    }

}