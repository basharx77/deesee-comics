package com.example.deeseecomics.api.controller;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.service.SuperheroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;
import java.util.List;

import static com.example.deeseecomics.util.Model2DtoMapper.mapModelSuperheroesToDtos;
import static com.example.deeseecomics.util.Dto2ModelMapper.mapSuperpowersDtoToModel;

@RestController
@RequestMapping("/superheroes")
class SuperheroesController {

    private final SuperheroService superheroService;

    public SuperheroesController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping
    public List<SuperheroDTO> getSuperHeroes(
            @RequestParam(name = "superpowers", required = false, defaultValue = "") EnumSet<SuperpowerDTO> superpowersDto,
            @RequestParam(required = false, defaultValue = "false") Boolean encryptedIdentities) {

        return mapModelSuperheroesToDtos(
                superheroService.getSuperHeroes(mapSuperpowersDtoToModel(superpowersDto), encryptedIdentities));
    }

}
