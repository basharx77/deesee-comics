package com.example.deeseecomics.data.loader;

import com.example.deeseecomics.TestDataSupport;
import com.example.deeseecomics.domain.model.Superhero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuperheroDataLoaderTest extends TestDataSupport {

    @Spy
    private Resource resource = new ClassPathResource("testdata/superheroes.json");

    @InjectMocks
    private SuperheroDataLoader dataLoader;

    @Test
    void shouldReturnListOfSuperheroes() throws Exception {
        List<Superhero> result = dataLoader.loadSuperheroes();

        assertEquals(5, result.size());
    }

    @Test
    void shouldThrowIOException() throws Exception {
        when(resource.getInputStream()).thenThrow(new IOException());

        Exception exception = assertThrows(RuntimeException.class, () -> dataLoader.loadSuperheroes());

        assertInstanceOf(IOException.class, exception.getCause());
    }
}
