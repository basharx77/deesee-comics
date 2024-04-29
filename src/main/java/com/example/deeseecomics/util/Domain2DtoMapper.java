package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperheroDTO;
import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Domain2DtoMapper {

    public static List<SuperheroDTO> mapDomainSuperheroesToDtos(List<Superhero> superheroes) {
        return superheroes.stream().map(Domain2DtoMapper::mapDomainSuperheroToDto).toList();
    }

    public static SuperheroDTO mapDomainSuperheroToDto(Superhero superhero) {

        return new SuperheroDTO(superhero.getName(),
                "$%s$%s".formatted(superhero.getIdentity().getFirstName(), superhero.getIdentity().getLastName()),
                mapDomainSuperpowersToDtos(superhero.getSuperpowers()),
                superhero.getBirthday());
    }

    public static EnumSet<SuperpowerDTO> mapDomainSuperpowersToDtos(EnumSet<Superpower> superpowers) {
        return superpowers.
                stream().
                map(Domain2DtoMapper::mapDomainSuperpowerToDto).
                collect(Collectors.toCollection(() -> EnumSet.noneOf(SuperpowerDTO.class)));
    }

    public static SuperpowerDTO mapDomainSuperpowerToDto(Superpower superpower) {
        return SuperpowerDTO.valueOf(superpower.toString().toUpperCase());
    }


}
