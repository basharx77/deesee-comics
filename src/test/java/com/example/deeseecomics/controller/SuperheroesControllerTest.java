package com.example.deeseecomics.controller;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.domain.model.Superpower;
import com.example.deeseecomics.service.SuperheroService;
import com.example.deeseecomics.util.DomainModelsToDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static com.example.deeseecomics.MockMvcTestUtils.preformMockMvc;
import static com.example.deeseecomics.TestAssertionHelper.assertSuperheroes;
import static com.example.deeseecomics.TestData.*;
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

    private static List<String> getSuperpowersStringList(EnumSet<Superpower> superpowers) {
        return superpowers.
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                toList();
    }

    @Test
    void shouldReturn_AllSuperheroes_WithoutEncryptedIdentities_WhenEncryptionParamIsFalse() throws Exception {
        given(superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), false)).willReturn(
                Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        MultiValueMap<String, String> queryPrams = new LinkedMultiValueMap<>();
        queryPrams.add(ENCRYPTION_QUERY_PARAM, Boolean.FALSE.toString());

        String response = preformMockMvc(mockMvc, get(SUPERHEROES_CONTROLLER_PATH), queryPrams,
                MediaType.APPLICATION_JSON, status().isOk());

        assertSuperheroes(response, Collections.singletonList(DomainModelsToDtoMapper.
                mapDomainSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);

    }

    @Test
    void shouldReturn_AllSuperheroes_WithoutEncryptedIdentities_WhenEncryptionParamIsMissing() throws Exception {
        given(superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), false)).willReturn(
                Collections.singletonList(testData.FIRST_TEST_SUPERHERO));

        String response = preformMockMvc(mockMvc, get(SUPERHEROES_CONTROLLER_PATH), new LinkedMultiValueMap<>(),
                MediaType.APPLICATION_JSON, status().isOk());

        assertSuperheroes(response, Collections.singletonList(DomainModelsToDtoMapper.
                mapDomainSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);

    }

    @Test
    void shouldReturn_OnlySuperheroesWithGivenSuperpowers_AndEncryptedIdentities() throws Exception {
        given(superheroService.getSuperHeroes(testData.FIRST_TEST_SUPERHERO.getSuperpowers(), true)).
                willReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        MultiValueMap<String, String> queryPrams = new LinkedMultiValueMap<>();
        queryPrams.addAll(SUPERPOWER_QUERY_PARAM, getSuperpowersStringList(testData.FIRST_TEST_SUPERHERO.
                getSuperpowers()));
        queryPrams.add(ENCRYPTION_QUERY_PARAM, Boolean.TRUE.toString());

        String response = preformMockMvc(mockMvc, get(SUPERHEROES_CONTROLLER_PATH), queryPrams,
                MediaType.APPLICATION_JSON,
                status().isOk());

        assertSuperheroes(response, Collections.singletonList(
                DomainModelsToDtoMapper.mapDomainSuperheroToDto(testData.FIRST_TEST_SUPERHERO)), objectMapper);
    }
}
