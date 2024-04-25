package com.example.deeseecomics.data;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.data.loader.SuperheroDataLoader;
import com.example.deeseecomics.domain.model.Superhero;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemorySuperheroDataManagerTest {
    private final TestData testData = new TestData();
    private final SuperheroDataLoader superheroDataLoader = Mockito.mock(SuperheroDataLoader.class);
    private final InMemorySuperheroDataManager dataManager = new InMemorySuperheroDataManager(superheroDataLoader);

    @BeforeAll
    public void beforeAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(superheroDataLoader.loadSuperheroes()).thenReturn(List.of(testData.FIRST_TEST_SUPERHERO,
                testData.SECOND_TEST_SUPERHERO));
        Method method = InMemorySuperheroDataManager.class.getDeclaredMethod("loadSuperheroes");
        method.setAccessible(true);
        method.invoke(dataManager);
    }

    @Test
    public void shouldReturn_AllSuperheroes() {
        List<Superhero> superheroes = dataManager.getSuperHeroes();
        assertEquals(List.of(testData.FIRST_TEST_SUPERHERO, testData.SECOND_TEST_SUPERHERO), superheroes);
    }

    @Test
    public void shouldReturn_OnlySuperheroes_WithGivenSuperpowers() {
        List<Superhero> superheroes = dataManager.getSuperHeroesBySuperpowers(testData.FIRST_TEST_SUPERHERO.
                getSuperpowers());
        assertEquals(List.of(testData.FIRST_TEST_SUPERHERO), superheroes);

    }
}
