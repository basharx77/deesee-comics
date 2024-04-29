package com.example.deeseecomics.util;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static com.example.deeseecomics.TestAssertionHelper.assertSuperheroDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Domain2DtoMapperTest {

    private final TestData testData = new TestData();

    @Test
    public void shouldMapDomainSuperheroToDto() {
        var superhero = Domain2DtoMapper.mapDomainSuperheroToDto(testData.FIRST_TEST_SUPERHERO);

        assertSuperheroDto(superhero,
                testData.FIRST_TEST_SUPERHERO_NAME,
                testData.FIRST_TEST_SUPERHERO_BIRTHDAY,
                Domain2DtoMapper.mapDomainSuperpowersToDtos(testData.FIRST_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(testData.FIRST_TEST_SUPERHERO_FIRST_NAME,
                        testData.FIRST_TEST_SUPERHERO_SECOND_NAME));
    }

    @Test
    public void shouldMapDomainSuperheroesToDtos() {
        var superheroes = Domain2DtoMapper.mapDomainSuperheroesToDtos(List.of(
                testData.FIRST_TEST_SUPERHERO,
                testData.SECOND_TEST_SUPERHERO));


        assertSuperheroDto(superheroes.get(0),
                testData.FIRST_TEST_SUPERHERO_NAME,
                testData.FIRST_TEST_SUPERHERO_BIRTHDAY,
                Domain2DtoMapper.mapDomainSuperpowersToDtos(testData.FIRST_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(testData.FIRST_TEST_SUPERHERO_FIRST_NAME,
                        testData.FIRST_TEST_SUPERHERO_SECOND_NAME));

        assertSuperheroDto(superheroes.get(1),
                testData.SECOND_TEST_SUPERHERO_NAME,
                testData.SECOND_TEST_SUPERHERO_BIRTHDAY,
                Domain2DtoMapper.mapDomainSuperpowersToDtos(testData.SECOND_TEST_SUPERHERO_SUPERPOWERS),
                "$%s$%s".formatted(testData.SECOND_TEST_SUPERHERO_FIRST_NAME,
                        testData.SECOND_TEST_SUPERHERO_SECOND_NAME));
    }

    @Test
    public void shouldMapDomainSuperpowerToDto() {
        assertEquals(Domain2DtoMapper.mapDomainSuperpowerToDto(Superpower.FLIGHT),
                SuperpowerDTO.FLIGHT);
    }

    @Test
    public void shouldMapDomainSuperpowersToDtos() {
        var superpowerDtos = Domain2DtoMapper.mapDomainSuperpowersToDtos(EnumSet.allOf(Superpower.class));
        assertEquals(superpowerDtos, EnumSet.allOf(SuperpowerDTO.class));
    }

}