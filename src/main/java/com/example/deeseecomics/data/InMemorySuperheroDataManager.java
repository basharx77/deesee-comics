package com.example.deeseecomics.data;

import com.example.deeseecomics.data.loader.SuperheroDataLoader;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

@Component
public class InMemorySuperheroDataManager implements SuperheroDataManager {

    private final List<Superhero> superheroesList = new ArrayList<>();

    private final SuperheroDataLoader superheroDataLoader;

    public InMemorySuperheroDataManager(SuperheroDataLoader superheroDataLoader) {
        this.superheroDataLoader = superheroDataLoader;
    }

    public List<Superhero> getSuperHeroes() {
        return new ArrayList<>(superheroesList);
    }

    public List<Superhero> getSuperHeroesBySuperpowers(EnumSet<Superpower> superpowers) {
        Predicate<Superhero> superPowerFilter = superhero -> superhero.getSuperpowers().containsAll(superpowers);
        return superheroesList.stream().filter(superPowerFilter).toList();
    }

    @PostConstruct
    private void loadSuperheroes() {
        superheroesList.addAll(superheroDataLoader.loadSuperheroes());
    }
}
