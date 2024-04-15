package com.example.deeseecomics.service;

import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;

import java.util.EnumSet;
import java.util.List;

public interface SuperheroService {
    List<Superhero> getSuperHeroes(EnumSet<Superpower> superpowers, boolean encryption);
}
