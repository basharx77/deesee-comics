package com.example.deeseecomics.service;

import com.example.deeseecomics.data.InMemorySuperheroDataManager;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;
import com.example.deeseecomics.encryptor.SuperheroIdentityEncryptor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class InMemorySuperheroService implements SuperheroService {

    private final InMemorySuperheroDataManager inMemorySuperheroDataManager;

    private final SuperheroIdentityEncryptor superheroIdentityEncryptor;

    public InMemorySuperheroService(InMemorySuperheroDataManager inMemorySuperheroDataManager,
                                    SuperheroIdentityEncryptor superheroIdentityEncryptor) {
        this.inMemorySuperheroDataManager = inMemorySuperheroDataManager;
        this.superheroIdentityEncryptor = superheroIdentityEncryptor;
    }

    public List<Superhero> getSuperHeroes(EnumSet<Superpower> superpowers, boolean encryptedIdentities) {
        List<Superhero> superheroes = superpowers.isEmpty() ?
                inMemorySuperheroDataManager.getSuperHeroes() :
                inMemorySuperheroDataManager.getSuperHeroesBySuperpowers(superpowers);

        return encryptedIdentities ?
                superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(superheroes) :
                superheroes;
    }

}
