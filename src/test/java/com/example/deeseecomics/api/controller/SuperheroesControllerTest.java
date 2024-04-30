package com.example.deeseecomics.api.controller;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.api.controller.SuperheroesController;
import com.example.deeseecomics.model.Superpower;
import com.example.deeseecomics.service.SuperheroService;
import com.example.deeseecomics.util.Model2DtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.EnumSet;
import java.util.stream.Collectors;

import static com.example.deeseecomics.TestAssertionHelper.assertSuperheroes;
import static com.example.deeseecomics.TestData.*;
import static com.example.deeseecomics.WebTestUtils.getUrlWithQueryParams;
import static com.example.deeseecomics.WebTestUtils.preformMockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperheroesController.class)
public class SuperheroesControllerTest {
    private final TestData testData = new TestData();
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SuperheroService superheroService;
    @InjectMocks
    private SuperheroesController superheroesController;
    @Autowired
    private MockMvc mockMvc;

    private static String getSuperpowersStringList(EnumSet<Superpower> superpowers) {
        return superpowers.
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                collect(Collectors.joining(","));
    }


    @Test
    void should_GetAllSuperheroes_WithOutEncryptedIdentities_WithOutGivenSuperpowers() throws Exception {
        given(superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), false)).willReturn(
                Collections.singletonList(testData.FIRST_TEST_SUPERHERO));

        String response = preformMockMvc(mockMvc, get(SUPERHEROES_CONTROLLER_PATH), MediaType.APPLICATION_JSON,
                status().isOk());

        assertSuperheroes(response, Collections.singletonList(Model2DtoMapper.
                mapModelSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);

    }

    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities_WithOutGivenSuperpowers() throws Exception {
        given(superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), true)).
                willReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        String urlWithQueryParams = getUrlWithQueryParams(SUPERHEROES_CONTROLLER_PATH,
                ENCRYPTION_QUERY_PARAM, Boolean.TRUE.toString());

        String response = preformMockMvc(mockMvc, get(urlWithQueryParams), MediaType.APPLICATION_JSON, status().isOk());

        assertSuperheroes(response, Collections.singletonList(
                Model2DtoMapper.mapModelSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);
    }

    @Test
    void should_GetAllSuperheroes_WithOutEncryptedIdentities_WithGivenSuperpowers() throws Exception {
        given(superheroService.getSuperHeroes(testData.FIRST_TEST_SUPERHERO.getSuperpowers(), false)).
                willReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        String urlWithQueryParams = getUrlWithQueryParams(SUPERHEROES_CONTROLLER_PATH, SUPERPOWER_QUERY_PARAM,
                getSuperpowersStringList(testData.FIRST_TEST_SUPERHERO.getSuperpowers()), ENCRYPTION_QUERY_PARAM,
                Boolean.FALSE.toString());

        String response = preformMockMvc(mockMvc, get(urlWithQueryParams), MediaType.APPLICATION_JSON, status().isOk());

        assertSuperheroes(response, Collections.singletonList(
                Model2DtoMapper.mapModelSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);
    }

    @Test
    void should_GetAllSuperheroes_WithEncryptedIdentities_WithGivenSuperpowers() throws Exception {
        given(superheroService.getSuperHeroes(testData.FIRST_TEST_SUPERHERO.getSuperpowers(), true)).
                willReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        String urlWithQueryParams = getUrlWithQueryParams(SUPERHEROES_CONTROLLER_PATH, SUPERPOWER_QUERY_PARAM,
                getSuperpowersStringList(testData.FIRST_TEST_SUPERHERO.getSuperpowers()), ENCRYPTION_QUERY_PARAM,
                Boolean.TRUE.toString());

        String response = preformMockMvc(mockMvc, get(urlWithQueryParams), MediaType.APPLICATION_JSON, status().isOk());

        assertSuperheroes(response, Collections.singletonList(
                Model2DtoMapper.mapModelSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);
    }
}
