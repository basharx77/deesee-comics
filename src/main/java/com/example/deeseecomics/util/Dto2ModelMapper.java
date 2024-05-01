package com.example.deeseecomics.util;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superpower;

import java.util.EnumSet;
import java.util.stream.Collectors;

public class Dto2ModelMapper {

    public static EnumSet<Superpower> mapSuperpowerDtosToModels(EnumSet<SuperpowerDTO> superpowers) {
        return superpowers.
                stream().
                map(Dto2ModelMapper::mapSuperpowerDtoToModel).
                collect(Collectors.toCollection(() -> EnumSet.noneOf(Superpower.class)));
    }

    public static Superpower mapSuperpowerDtoToModel(SuperpowerDTO superpower) {
        return Superpower.valueOf(superpower.toString().toUpperCase());
    }
}
