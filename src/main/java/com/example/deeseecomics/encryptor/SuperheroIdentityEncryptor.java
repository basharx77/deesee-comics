package com.example.deeseecomics.encryptor;

import com.example.deeseecomics.domain.model.Identity;
import com.example.deeseecomics.domain.model.Superhero;
import com.example.deeseecomics.encryptor.string.StringEncryptor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuperheroIdentityEncryptor {

    private final StringEncryptor stringEncryptor;

    public SuperheroIdentityEncryptor(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public List<Superhero> createNewSuperheroesWithEncryptedIdentities(List<Superhero> superheroes) {
        return superheroes.stream().map(this::getNewSuperheroWithEncryptedIdentity).toList();
    }

    private Superhero getNewSuperheroWithEncryptedIdentity(Superhero superhero) {
        Identity encryptedidentity = getNewEncryptedIdentity(superhero.getIdentity());

        return createShallowCopyOfSuperheroWithEncryptedIdentity(superhero, encryptedidentity);
    }

    private Identity getNewEncryptedIdentity(Identity identity) {
        Identity encryptedIdentity = new Identity();
        encryptedIdentity.setFirstName(stringEncryptor.encryptString(identity.getFirstName()));
        encryptedIdentity.setLastName(stringEncryptor.encryptString(identity.getLastName()));

        return encryptedIdentity;
    }

    private Superhero createShallowCopyOfSuperheroWithEncryptedIdentity(Superhero superhero, Identity identity) {
        Superhero superheroWihtEncryptedIndetities = new Superhero();
        superheroWihtEncryptedIndetities.setName(superhero.getName());
        superheroWihtEncryptedIndetities.setBirthday(superhero.getBirthday());
        superheroWihtEncryptedIndetities.setSuperpowers(superhero.getSuperpowers());
        superheroWihtEncryptedIndetities.setIdentity(identity);

        return superheroWihtEncryptedIndetities;
    }
}
