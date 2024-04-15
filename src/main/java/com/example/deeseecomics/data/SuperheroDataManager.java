package com.example.deeseecomics.data;

import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;

import java.util.EnumSet;
import java.util.List;

public interface SuperheroDataManager {
    List<Superhero> getSuperHeroes();

    List<Superhero> getSuperHeroesBySuperpowers(EnumSet<Superpower> superpowers);
}
