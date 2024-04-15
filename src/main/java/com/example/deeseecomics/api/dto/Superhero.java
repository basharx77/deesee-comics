package com.example.deeseecomics.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Objects;

public class Superhero {

    private String name;
    private String identity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private EnumSet<Superpower> superpowers;

    public Superhero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public EnumSet<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(EnumSet<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superhero superhero = (Superhero) o;
        return Objects.equals(name, superhero.name) && Objects.equals(identity, superhero.identity) && Objects.equals(birthday, superhero.birthday) && Objects.equals(superpowers, superhero.superpowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, identity, birthday, superpowers);
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", birthday=" + birthday +
                ", superpowers=" + superpowers +
                '}';
    }
}