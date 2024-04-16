package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.Superhero;
import com.example.deeseecomics.api.dto.Superpower;
import com.example.deeseecomics.domain.model.Identity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAssertionHelper {

    public static void assertSuperheroes(String response,
                                         List<Superhero> expectedSuperheroes,
                                         ObjectMapper objectMapper) throws JsonProcessingException {
        List<Superhero> superheroes = objectMapper.readValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Superhero.class));

        assertEquals(expectedSuperheroes, superheroes);
    }

    public static void assertIdentity(Identity identity, String expectedFirstName, String expectedLastName) {
        assertEquals(expectedFirstName, identity.getFirstName());
        assertEquals(expectedLastName, identity.getLastName());
    }

    public static void assertSuperheroDto(Superhero superhero, String name, LocalDate birthDay, EnumSet<Superpower> superpowers, String identity) {
        assertEquals(name, superhero.name());
        assertEquals(birthDay, superhero.birthday());
        assertEquals(superpowers, superhero.superpowers());
        assertEquals(identity, superhero.identity());
    }
}
