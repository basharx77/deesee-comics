package com.example.deeseecomics.encryptor.string;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
class DeeseeStringEncryptor implements StringEncryptor {

    private final Integer key;

    public DeeseeStringEncryptor(@Value("${cipher.deesee.key}") Integer key) {
        this.key = key;
    }

    public String encryptString(String value) {
        return value.chars().mapToObj(this::encryptChar).collect(Collectors.joining());
    }

    private String encryptChar(int value) {
        int newValue = Character.isWhitespace(value) ? value : value + key;
        return String.valueOf((char) newValue);
    }

}