package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.Superhero;
import com.example.deeseecomics.api.dto.Superpower;
import com.example.deeseecomics.data.loader.SuperheroDataLoader;
import com.example.deeseecomics.encryptor.SuperheroIdentityEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.deeseecomics.util.DomainModelsToDtoMapper.mapDomainSuperheroToDto;
import static com.example.deeseecomics.util.DomainModelsToDtoMapper.mapDomainSuperheroesToDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DeeseeComicsApplicationIntegrationTest extends TestDataSupport {

    private final static String SUPERHEROES_CONTROLLER_PATH = "/superheroes";

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private SuperheroDataLoader superheroDataLoader;
    @Autowired
    private SuperheroIdentityEncryptor superheroIdentityEncryptor;

    @Test
    void should_GetAllSuperheroes_WithoutEncryptedIdentities() {
        Superhero[] superheroes = getSuperHeroes(null, null);

        assertEquals(5, superheroes.length);
        assertEquals(mapDomainSuperheroesToDtos(superheroDataLoader.loadSuperheroes()), List.of(superheroes));
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities() {
        Superhero[] superheroes = getSuperHeroes(true, null);

        assertEquals(5, superheroes.length);
        assertEquals(mapDomainSuperheroesToDtos(superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                superheroDataLoader.loadSuperheroes())), List.of(superheroes));
    }

    @Test
    void should_GetAllSuperheroes_WithOutEncryptedIdentities_WithGivenSuperpowers() {
        Superhero[] superheroes = getSuperHeroes(null,
                concatSuperpowers(Superpower.FLIGHT, Superpower.HEALING, Superpower.STRENGTH));

        assertEquals(1, superheroes.length);
        assertEquals(mapDomainSuperheroToDto(superheroDataLoader.loadSuperheroes().get(3)), superheroes[0]);
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities_WithGivenSuperpowers() {
        Superhero[] superheroes = getSuperHeroes(true,
                concatSuperpowers(Superpower.FLIGHT, Superpower.HEALING, Superpower.STRENGTH));

        assertEquals(1, superheroes.length);
        var expectedSuperhero = superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                List.of(superheroDataLoader.loadSuperheroes().get(3))).get(0);
        assertEquals(mapDomainSuperheroToDto((expectedSuperhero)), superheroes[0]);
    }


    private Superhero[] getSuperHeroes(Boolean ecryptionQueryParamValue, String superpowerQueryParamValue) {
        var ecryptionValue = ecryptionQueryParamValue == null ? null : ecryptionQueryParamValue.toString();
        var uriBuilder = UriComponentsBuilder.fromHttpUrl(testRestTemplate.getRootUri() + SUPERHEROES_CONTROLLER_PATH)
                .queryParam(ENCRYPTION_QUERY_PARAM, ecryptionValue)
                .queryParam(SUPERPOWER_QUERY_PARAM, superpowerQueryParamValue);

        return testRestTemplate.getForObject(uriBuilder.toUriString(), Superhero[].class);
    }

    private String concatSuperpowers(Superpower... superpowers) {

        return EnumSet.copyOf(List.of(superpowers)).
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                collect(Collectors.joining(","));
    }


}
