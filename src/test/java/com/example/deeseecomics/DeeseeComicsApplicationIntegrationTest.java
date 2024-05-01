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

import java.util.List;

import static com.example.deeseecomics.TestData.*;
import static com.example.deeseecomics.WebTestUtils.concatSuperpowers;
import static com.example.deeseecomics.WebTestUtils.getUrlWithQueryParams;
import static com.example.deeseecomics.util.Model2DtoMapper.mapSuperheroModelToDto;
import static com.example.deeseecomics.util.Model2DtoMapper.mapSuperheroModelsToDtos;
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
        assertEquals(mapSuperheroModelsToDtos(superheroDataLoader.loadSuperheroes()), List.of(superheroDTOS));
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(true, null);

        assertEquals(5, superheroDTOS.length);
        assertEquals(mapSuperheroModelsToDtos(superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                superheroDataLoader.loadSuperheroes())), List.of(superheroDTOS));
    }

    @Test
    void should_GetAllSuperheroes_WithOutEncryptedIdentities_WithGivenSuperpowers() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(null,
                concatSuperpowers(SuperpowerDTO.FLIGHT, SuperpowerDTO.HEALING, SuperpowerDTO.STRENGTH));

        assertEquals(1, superheroDTOS.length);
        assertEquals(mapSuperheroModelToDto(superheroDataLoader.loadSuperheroes().get(3)), superheroDTOS[0]);
    }


    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities_WithGivenSuperpowers() {
        SuperheroDTO[] superheroDTOS = getSuperHeroes(true,
                concatSuperpowers(SuperpowerDTO.FLIGHT, SuperpowerDTO.HEALING, SuperpowerDTO.STRENGTH));

        assertEquals(1, superheroDTOS.length);
        var expectedSuperhero = superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                List.of(superheroDataLoader.loadSuperheroes().get(3))).get(0);
        assertEquals(mapSuperheroModelToDto((expectedSuperhero)), superheroDTOS[0]);
    }


    private SuperheroDTO[] getSuperHeroes(Boolean ecryptionQueryParamValue, String superpowerQueryParamValue) {
        var ecryptionValue = ecryptionQueryParamValue == null ? null : ecryptionQueryParamValue.toString();
        var urlWithQueriesPrams = getUrlWithQueryParams(testRestTemplate.getRootUri() + SUPERHEROES_CONTROLLER_PATH,
                ENCRYPTION_QUERY_PARAM, ecryptionValue, SUPERPOWER_QUERY_PARAM, superpowerQueryParamValue);

        return testRestTemplate.getForObject(urlWithQueriesPrams, SuperheroDTO[].class);
    }

}
