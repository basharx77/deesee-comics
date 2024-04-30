package com.example.deeseecomics.encryptor.string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeeseeStringEncryptorTest {

    private static final DeeseeStringEncryptor deeseeStringEncryptor = new DeeseeStringEncryptor(4);

    @Test
    void testEncryptString_WithOutWhiteSpaces() {
        assertEquals("EFG", deeseeStringEncryptor.encryptString("ABC"));
    }

    @Test
    void testEncryptString_WithWhiteSpaces() {
        assertEquals("E F G", deeseeStringEncryptor.encryptString("A B C"));
    }
}
