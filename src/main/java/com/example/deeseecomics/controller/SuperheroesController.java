package com.example.deeseecomics.controller;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.domain.model.Superpower;
import com.example.deeseecomics.service.SuperheroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;
import java.util.List;

import static com.example.deeseecomics.util.DomainModelsToDtoMapper.mapDomainSuperheroesToDtos;

@RestController
@RequestMapping("/superheroes")
class SuperheroesController {

    private final SuperheroService superheroService;

    public SuperheroesController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping
    public List<SuperheroDTO> getSuperHeroes(
            @RequestParam(required = false, defaultValue = "") EnumSet<Superpower> superpowers,
            @RequestParam(required = false, defaultValue = "false") Boolean encryptedIdentities) {

        return mapDomainSuperheroesToDtos(superheroService.getSuperHeroes(superpowers, encryptedIdentities));
    }

}
