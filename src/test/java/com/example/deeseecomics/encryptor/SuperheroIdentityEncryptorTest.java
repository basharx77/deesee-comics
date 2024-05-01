package com.example.deeseecomics.encryptor;

import com.example.deeseecomics.TestData;
import com.example.deeseecomics.encryptor.string.StringEncryptor;
import com.example.deeseecomics.model.Identity;
import com.example.deeseecomics.model.Superhero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.deeseecomics.TestAssertionHelper.assertIdentity;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroIdentityEncryptorTest {

    private final static String TEST_ENCRYPTED_FORM = "ENCRYPTED_%s";
    private final TestData testData = new TestData();

    @Mock
    private StringEncryptor stringEncryptor;

    @InjectMocks
    private SuperheroIdentityEncryptor superheroIdentityEncryptor;

    @Test
    void shouldReturn_SuperheroesWithEncryptedIdentities() {
        prepareStubbing(testData.FIRST_TEST_SUPERHERO_FIRST_NAME, testData.FIRST_TEST_SUPERHERO_SECOND_NAME);
        prepareStubbing(testData.SECOND_TEST_SUPERHERO_FIRST_NAME, testData.SECOND_TEST_SUPERHERO_SECOND_NAME);

        List<Superhero> encryptedSuperheroes = superheroIdentityEncryptor.createNewSuperheroesWithEncryptedIdentities(
                List.of(testData.FIRST_TEST_SUPERHERO, testData.SECOND_TEST_SUPERHERO));

        assertEncryptedIdentity(encryptedSuperheroes.get(0).getIdentity(),
                testData.FIRST_TEST_SUPERHERO_FIRST_NAME, testData.FIRST_TEST_SUPERHERO_SECOND_NAME);
        assertEncryptedIdentity(encryptedSuperheroes.get(1).getIdentity()
                , testData.SECOND_TEST_SUPERHERO_FIRST_NAME, testData.SECOND_TEST_SUPERHERO_SECOND_NAME);
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
