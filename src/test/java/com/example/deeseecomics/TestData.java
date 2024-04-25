package com.example.deeseecomics;

import com.example.deeseecomics.domain.model.Identity;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;

import java.time.LocalDate;
import java.util.EnumSet;

public class TestData {


    // Superheroes Controller constants
    public final static String SUPERHEROES_CONTROLLER_PATH = "/superheroes";
    public static final String SUPERPOWER_QUERY_PARAM = "superpowers";
    public static final String ENCRYPTION_QUERY_PARAM = "encryptedIdentities";


    // Info of first test superhero
    public final String FIRST_TEST_SUPERHERO_NAME = "Superman";
    public final String FIRST_TEST_SUPERHERO_FIRST_NAME = "Max";
    public final String FIRST_TEST_SUPERHERO_SECOND_NAME = "Mustermann";
    public final LocalDate FIRST_TEST_SUPERHERO_BIRTHDAY = LocalDate.of(2000, 1, 1);
    public final EnumSet<Superpower> FIRST_TEST_SUPERHERO_SUPERPOWERS = EnumSet.allOf(Superpower.class);


    // Info of Second test superhero
    public final String SECOND_TEST_SUPERHERO_NAME = "Batman";
    public final String SECOND_TEST_SUPERHERO_FIRST_NAME = "John";
    public final String SECOND_TEST_SUPERHERO_SECOND_NAME = "Smith";
    public final LocalDate SECOND_TEST_SUPERHERO_BIRTHDAY = LocalDate.of(1999, 1, 1);
    public final EnumSet<Superpower> SECOND_TEST_SUPERHERO_SUPERPOWERS = EnumSet.of(Superpower.FLIGHT);

    public final Superhero FIRST_TEST_SUPERHERO = getFirstTestSuperHero();
    public final Superhero SECOND_TEST_SUPERHERO = getSecondTestSuperHero();

    private Superhero getFirstTestSuperHero() {
        Superhero superhero = new Superhero();
        superhero.setName(FIRST_TEST_SUPERHERO_NAME);
        superhero.setBirthday(FIRST_TEST_SUPERHERO_BIRTHDAY);
        superhero.setIdentity(getTestIdentityOfFirstTestSuperHero());
        superhero.setSuperpowers(FIRST_TEST_SUPERHERO_SUPERPOWERS);

        return superhero;
    }

    private Identity getTestIdentityOfFirstTestSuperHero() {
        Identity identity = new Identity();
        identity.setFirstName(FIRST_TEST_SUPERHERO_FIRST_NAME);
        identity.setLastName(FIRST_TEST_SUPERHERO_SECOND_NAME);

        return identity;

    }

    private Superhero getSecondTestSuperHero() {
        Superhero superhero = new Superhero();
        superhero.setName(SECOND_TEST_SUPERHERO_NAME);
        superhero.setBirthday(SECOND_TEST_SUPERHERO_BIRTHDAY);
        superhero.setIdentity(getTestIdentityOfSecondTestSuperHero());
        superhero.setSuperpowers(SECOND_TEST_SUPERHERO_SUPERPOWERS);

        return superhero;
    }

    private Identity getTestIdentityOfSecondTestSuperHero() {
        Identity identity = new Identity();
        identity.setFirstName(SECOND_TEST_SUPERHERO_FIRST_NAME);
        identity.setLastName(SECOND_TEST_SUPERHERO_SECOND_NAME);

        return identity;

    }
}
