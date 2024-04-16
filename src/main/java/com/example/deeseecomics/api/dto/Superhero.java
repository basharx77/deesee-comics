package com.example.deeseecomics.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Objects;

public record Superhero(String name, String identity,EnumSet<Superpower> superpowers,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                        LocalDate birthday) {
}