package com.example.deeseecomics.encryptor.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeeseeStringEncryptorTest {

    private static final String LATIN_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String LATIN_LETTERS_WITH_WHITE_SPACES = """
            A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
            a b c d e f g h i j k l m n o p q r s t u v w x y z""";

    private static final DeeseeStringEncryptor deeseeStringEncryptor = new DeeseeStringEncryptor(3);

    @Test
    void testEncryptString_WithOutWhiteSpaces() {
        String result = deeseeStringEncryptor.encryptString(LATIN_LETTERS);
        String expectedResult = "DEFGHIJKLMNOPQRSTUVWXYZABCdefghijklmnopqrstuvwxyzabc";

        assertEquals(LATIN_LETTERS.length(), result.length());
        assertEquals(expectedResult, result);
    }

    @Test
    void testEncryptString_WithWhiteSpaces() {
        String result = deeseeStringEncryptor.encryptString(LATIN_LETTERS_WITH_WHITE_SPACES);
        String expectedResult = """
                D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
                d e f g h i j k l m n o p q r s t u v w x y z a b c""";

        assertEquals(LATIN_LETTERS_WITH_WHITE_SPACES.length(), result.length());
        assertEquals(expectedResult, result);
    }
}
