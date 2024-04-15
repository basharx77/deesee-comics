package com.example.deeseecomics;

import com.example.deeseecomics.domain.model.Identity;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.domain.model.Superpower;

import java.time.LocalDate;
import java.util.EnumSet;

public class TestDataSupport {

    protected final String SUPERPOWER_QUERY_PARAM = "superpowers";
    protected final String ENCRYPTION_QUERY_PARAM = "encryptedIdentities";
    protected final String FIRST_TEST_SUPERHERO_NAME = "Superman";
    protected final String FIRST_TEST_SUPERHERO_FIRST_NAME = "Max";
    protected final String FIRST_TEST_SUPERHERO_SECOND_NAME = "Mustermann";
    protected final LocalDate FIRST_TEST_SUPERHERO_BIRTHDAY = LocalDate.of(2000, 1, 1);
    protected final EnumSet<Superpower> FIRST_TEST_SUPERHERO_SUPERPOWERS = EnumSet.allOf(Superpower.class);

    protected final String SECOND_TEST_SUPERHERO_NAME = "Batman";
    protected final String SECOND_TEST_SUPERHERO_FIRST_NAME = "John";
    protected final String SECOND_TEST_SUPERHERO_SECOND_NAME = "Smith";
    protected final LocalDate SECOND_TEST_SUPERHERO_BIRTHDAY = LocalDate.of(1999, 1, 1);
    protected final EnumSet<Superpower> SECOND_TEST_SUPERHERO_SUPERPOWERS = EnumSet.of(Superpower.FLIGHT);

    protected final Superhero FIRST_TEST_SUPERHERO = getFirstTestSuperHero();
    protected final Superhero SECOND_TEST_SUPERHERO = getSecondTestSuperHero();

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
