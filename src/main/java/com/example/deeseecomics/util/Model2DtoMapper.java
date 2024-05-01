package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superhero;
import com.example.deeseecomics.model.Superpower;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Model2DtoMapper {

    public static List<SuperheroDTO> mapSuperheroModelsToDtos(List<Superhero> superheroes) {
        return superheroes.stream().map(Model2DtoMapper::mapSuperheroModelToDto).toList();
    }

    public static SuperheroDTO mapSuperheroModelToDto(Superhero superhero) {

        return new SuperheroDTO(superhero.getName(),
                "$%s$%s".formatted(superhero.getIdentity().getFirstName(), superhero.getIdentity().getLastName()),
                mapSuperpowerModelsToDtos(superhero.getSuperpowers()),
                superhero.getBirthday());
    }

    public static EnumSet<SuperpowerDTO> mapSuperpowerModelsToDtos(EnumSet<Superpower> superpowers) {
        return superpowers.
                stream().
                map(Model2DtoMapper::mapSuperpowerModelToDto).
                collect(Collectors.toCollection(() -> EnumSet.noneOf(SuperpowerDTO.class)));
    }

    public static SuperpowerDTO mapSuperpowerModelToDto(Superpower superpower) {
        return SuperpowerDTO.valueOf(superpower.toString().toUpperCase());
    }


}
