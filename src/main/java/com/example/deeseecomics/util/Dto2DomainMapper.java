package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;

import java.util.EnumSet;
import java.util.stream.Collectors;

public class Dto2DomainMapper {

    public static EnumSet<Superpower> mapSuperpowersDtoToDomain(EnumSet<SuperpowerDTO> superpowers) {
        return superpowers.
                stream().
                map(Dto2DomainMapper::mapSuperpowerDtoToDomain).
                collect(Collectors.toCollection(() -> EnumSet.noneOf(Superpower.class)));
    }

    public static Superpower mapSuperpowerDtoToDomain(SuperpowerDTO superpower) {
        return Superpower.valueOf(superpower.toString().toUpperCase());
    }
}
