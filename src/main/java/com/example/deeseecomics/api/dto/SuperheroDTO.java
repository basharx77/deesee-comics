package com.example.deeseecomics.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.EnumSet;

public record SuperheroDTO(String name, String identity, EnumSet<SuperpowerDTO> superpowerDTOS,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                        LocalDate birthday) {
}