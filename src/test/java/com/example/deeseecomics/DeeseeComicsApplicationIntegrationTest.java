package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
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

import static com.example.deeseecomics.TestData.*;
import static com.example.deeseecomics.util.DomainModelsToDtoMapper.mapDomainSuperheroToDto;
import static com.example.deeseecomics.util.DomainModelsToDtoMapper.mapDomainSuperheroesToDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DeeseeComicsApplicationIntegrationTest {

    private final TestData testData = new TestData();
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private SuperheroDataLoader superheroDataLoader;
    @Autowired
    private SuperheroIdentityEncryptor superheroIdentityEncryptor;

    @Test
    void should_GetAllSuperheroes_WithoutEncryptedIdentities() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(null, null);

        assertEquals(5, superheroDTOS.length);
        assertEquals(mapDomainSuperheroesToDtos(superheroDataLoader.loadSuperheroes()), List.of(superheroDTOS));
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(true, null);

        assertEquals(5, superheroDTOS.length);
        assertEquals(mapDomainSuperheroesToDtos(superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                superheroDataLoader.loadSuperheroes())), List.of(superheroDTOS));
    }

    @Test
    void should_GetAllSuperheroes_WithOutEncryptedIdentities_WithGivenSuperpowers() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(null,
                concatSuperpowers(SuperpowerDTO.FLIGHT, SuperpowerDTO.HEALING, SuperpowerDTO.STRENGTH));

        assertEquals(1, superheroDTOS.length);
        assertEquals(mapDomainSuperheroToDto(superheroDataLoader.loadSuperheroes().get(3)), superheroDTOS[0]);
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities_WithGivenSuperpowers() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(true,
                concatSuperpowers(SuperpowerDTO.FLIGHT, SuperpowerDTO.HEALING, SuperpowerDTO.STRENGTH));

        assertEquals(1, superheroDTOS.length);
        var expectedSuperhero = superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                List.of(superheroDataLoader.loadSuperheroes().get(3))).get(0);
        assertEquals(mapDomainSuperheroToDto((expectedSuperhero)), superheroDTOS[0]);
    }


    private SuperheroDTO[] getSuperHeroes(Boolean ecryptionQueryParamValue, String superpowerQueryParamValue) {
        var ecryptionValue = ecryptionQueryParamValue == null ? null : ecryptionQueryParamValue.toString();
        var uriBuilder = UriComponentsBuilder.fromHttpUrl(testRestTemplate.getRootUri() + SUPERHEROES_CONTROLLER_PATH)
                .queryParam(ENCRYPTION_QUERY_PARAM, ecryptionValue)
                .queryParam(SUPERPOWER_QUERY_PARAM, superpowerQueryParamValue);

        return testRestTemplate.getForObject(uriBuilder.toUriString(), SuperheroDTO[].class);
    }

    private String concatSuperpowers(SuperpowerDTO... superpowerDTOS) {

        return EnumSet.copyOf(List.of(superpowerDTOS)).
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                collect(Collectors.joining(","));
    }


}
