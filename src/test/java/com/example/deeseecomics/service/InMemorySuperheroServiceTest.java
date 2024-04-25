package com.example.deeseecomics.service;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.data.InMemorySuperheroDataManager;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;
import com.example.deeseecomics.encryptor.SuperheroIdentityEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InMemorySuperheroServiceTest {
    private final TestData testData = new TestData();

    @Mock
    private InMemorySuperheroDataManager inMemorySuperheroDataManager;
    @Mock
    private SuperheroIdentityEncryptor superheroIdentityEncryptor;
    @InjectMocks
    private InMemorySuperheroService superheroService;

    @Test
    void test_GetSuperHeroes_WithGivenSuperpowers_WithEncryptedIdentitiesNeeded() {
        when(inMemorySuperheroDataManager.getSuperHeroesBySuperpowers(EnumSet.of(Superpower.FLIGHT))).
                thenReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        when(superheroIdentityEncryptor.
                createNewSuperheroesWithEncryptedIdentities(Collections.singletonList(testData.FIRST_TEST_SUPERHERO))).
                thenReturn(Collections.singletonList(testData.SECOND_TEST_SUPERHERO));

        List<Superhero> result = superheroService.getSuperHeroes(EnumSet.of(Superpower.FLIGHT), true);

        verify(inMemorySuperheroDataManager).getSuperHeroesBySuperpowers(EnumSet.of(Superpower.FLIGHT));
        verify(superheroIdentityEncryptor).createNewSuperheroesWithEncryptedIdentities(
                Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        assertEquals(result, Collections.singletonList(testData.SECOND_TEST_SUPERHERO));
    }

    @Test
    void test_GetSuperHeroes_WithoutGivenSuperpowers_WithEncryptedIdentitiesNeeded() {
        when(inMemorySuperheroDataManager.getSuperHeroes()).
                thenReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        when(superheroIdentityEncryptor.
                createNewSuperheroesWithEncryptedIdentities(Collections.singletonList(testData.FIRST_TEST_SUPERHERO))).
                thenReturn(Collections.singletonList(testData.SECOND_TEST_SUPERHERO));

        List<Superhero> result = superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), true);

        verify(inMemorySuperheroDataManager).getSuperHeroes();
        verify(superheroIdentityEncryptor).createNewSuperheroesWithEncryptedIdentities(
                Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
        assertEquals(result, Collections.singletonList(testData.SECOND_TEST_SUPERHERO));
    }

    @Test
    void test_GetSuperHeroes_WithGivenSuperpowers_WithoutEncryptedIdentitiesNeeded() {
        when(inMemorySuperheroDataManager.getSuperHeroesBySuperpowers(EnumSet.of(Superpower.FLIGHT))).
                thenReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));

        List<Superhero> result = superheroService.getSuperHeroes(EnumSet.of(Superpower.FLIGHT), false);

        verify(inMemorySuperheroDataManager).getSuperHeroesBySuperpowers(EnumSet.of(Superpower.FLIGHT));
        assertEquals(result, Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
    }

    @Test
    void test_GetSuperHeroes_WithoutGivenSuperpowers_WithoutEncryptedIdentitiesNeeded() {
        when(inMemorySuperheroDataManager.getSuperHeroes()).
                thenReturn(Collections.singletonList(testData.FIRST_TEST_SUPERHERO));

        List<Superhero> result = superheroService.getSuperHeroes(EnumSet.noneOf(Superpower.class), false);

        verify(inMemorySuperheroDataManager).getSuperHeroes();
        assertEquals(result, Collections.singletonList(testData.FIRST_TEST_SUPERHERO));
    }
}
