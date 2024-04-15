package com.example.deeseecomics.encryptor;

import com.example.deeseecomics.TestDataSupport;
import com.example.deeseecomics.domain.model.Identity;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.encryptor.string.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.deeseecomics.TestAssertionHelper.assertIdentity;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroIdentityEncryptorTest extends TestDataSupport {

    private final static String TEST_ENCRYPTED_FORM = "ENCRYPTED_%s";

    @Mock
    private StringEncryptor stringEncryptor;

    @InjectMocks
    private SuperheroIdentityEncryptor superheroIdentityEncryptor;

    @Test
    void shouldReturn_SuperheroesWithEncryptedIdentities() {
        prepareStubbing(FIRST_TEST_SUPERHERO_FIRST_NAME, FIRST_TEST_SUPERHERO_SECOND_NAME);
        prepareStubbing(SECOND_TEST_SUPERHERO_FIRST_NAME, SECOND_TEST_SUPERHERO_SECOND_NAME);

        List<Superhero> encryptedSuperheroes = superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                List.of(FIRST_TEST_SUPERHERO, SECOND_TEST_SUPERHERO));

        assertEncryptedIdentity(encryptedSuperheroes.get(0).getIdentity(),
                FIRST_TEST_SUPERHERO_FIRST_NAME, FIRST_TEST_SUPERHERO_SECOND_NAME);
        assertEncryptedIdentity(encryptedSuperheroes.get(1).getIdentity()
                , SECOND_TEST_SUPERHERO_FIRST_NAME, SECOND_TEST_SUPERHERO_SECOND_NAME);
    }

    private void prepareStubbing(String firstName, String secondName) {
        when(stringEncryptor.encryptString(firstName)).thenReturn(TEST_ENCRYPTED_FORM.formatted(firstName));
        when(stringEncryptor.encryptString(secondName)).thenReturn(TEST_ENCRYPTED_FORM.formatted(secondName));
    }

    private void assertEncryptedIdentity(Identity identity, String firstName, String secondName) {
        assertIdentity(identity, TEST_ENCRYPTED_FORM.formatted(firstName),
                TEST_ENCRYPTED_FORM.formatted(secondName));
    }
}
