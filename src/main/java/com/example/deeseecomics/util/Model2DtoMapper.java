package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superhero;
import com.example.deeseecomics.model.Superpower;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Model2DtoMapper {

    public static List<SuperheroDTO> mapModelSuperheroesToDtos(List<Superhero> superheroes) {
        return superheroes.stream().map(Model2DtoMapper::mapModelSuperheroToDto).toList();
    }

    public static SuperheroDTO mapModelSuperheroToDto(Superhero superhero) {

        return new SuperheroDTO(superhero.getName(),
                "$%s$%s".formatted(superhero.getIdentity().getFirstName(), superhero.getIdentity().getLastName()),
                mapModelSuperpowersToDtos(superhero.getSuperpowers()),
                superhero.getBirthday());
    }

    public static EnumSet<SuperpowerDTO> mapModelSuperpowersToDtos(EnumSet<Superpower> superpowers) {
        return superpowers.
                stream().
                map(Model2DtoMapper::mapModelSuperpowerToDto).
                collect(Collectors.toCollection(() -> EnumSet.noneOf(SuperpowerDTO.class)));
    }

    public static SuperpowerDTO mapModelSuperpowerToDto(Superpower superpower) {
        return SuperpowerDTO.valueOf(superpower.toString().toUpperCase());
    }


}
