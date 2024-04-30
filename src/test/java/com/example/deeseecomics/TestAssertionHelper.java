package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Identity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAssertionHelper {

    public static void assertSuperheroes(String response, List<SuperheroDTO> expectedSuperheroDTOS,
                                         ObjectMapper objectMapper) throws JsonProcessingException {
        List<SuperheroDTO> superheroDTOS = objectMapper.readValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, SuperheroDTO.class));

        assertEquals(expectedSuperheroDTOS, superheroDTOS);
    }

    public static void assertIdentity(Identity identity, String expectedFirstName, String expectedLastName) {
        assertEquals(expectedFirstName, identity.getFirstName());
        assertEquals(expectedLastName, identity.getLastName());
    }

    public static void assertSuperheroDto(SuperheroDTO superheroDTO, String name, LocalDate birthDay,
                                          EnumSet<SuperpowerDTO> superpowerDTOS, String identity) {
        assertEquals(name, superheroDTO.name());
        assertEquals(birthDay, superheroDTO.birthday());
        assertEquals(superpowerDTOS, superheroDTO.superpowers());
        assertEquals(identity, superheroDTO.identity());
    }
}
