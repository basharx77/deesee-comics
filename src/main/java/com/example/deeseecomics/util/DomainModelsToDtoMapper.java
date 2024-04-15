package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.Superhero;
import com.example.deeseecomics.api.dto.Superpower;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class DomainModelsToDtoMapper {

    public static List<Superhero> mapDomainSuperheroesToDtos(
            List<com.example.deeseecomics.domain.model.Superhero> superheroes){
        return superheroes.stream().map(DomainModelsToDtoMapper::mapDomainSuperheroToDto).toList();
    }

    public static Superhero mapDomainSuperheroToDto(
            com.example.deeseecomics.domain.model.Superhero superhero){
        var superheroDto = new Superhero();
        superheroDto.setName(superhero.getName());
        superheroDto.setBirthday(superhero.getBirthday());
        superheroDto.setSuperpowers(mapDomainSuperpowersToDtos(superhero.getSuperpowers()));
        String firstName = superhero.getIdentity().getFirstName();
        String lastName = superhero.getIdentity().getLastName();
        superheroDto.setIdentity("$%s$%s".formatted(firstName, lastName));

        return superheroDto;
    }

    public static EnumSet<Superpower> mapDomainSuperpowersToDtos(
            EnumSet<com.example.deeseecomics.domain.model.Superpower> superpowers){
        return superpowers.stream().map(DomainModelsToDtoMapper::mapDomainSuperpowerToDto).collect(
                Collectors.toCollection(() -> EnumSet.noneOf(Superpower.class)));
     }

    public static Superpower mapDomainSuperpowerToDto(
            com.example.deeseecomics.domain.model.Superpower superpower){
       return Superpower.valueOf(superpower.toString().toUpperCase());
    }
}
