import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lab8Test {

    @Test
    void lettersLatinTest() {
        assertEquals("A  B  C\n1  2  3", LetterNumbers.formatWithString("ABC"));
        assertEquals("A  B  C\n1  2  3", LetterNumbers.formatWithStream("ABC"));
    }

    @Test
    void lettersRussianTest() {
        assertEquals("А  я\n1  33", LetterNumbers.formatWithString("Ая"));
        assertEquals("А  я\n1  33", LetterNumbers.formatWithStream("Ая"));
    }

    @Test
    void lettersFilterTest() {
        assertEquals("A  B\n1  2", LetterNumbers.formatWithString("A1 B!"));
        assertEquals("A  B\n1  2", LetterNumbers.formatWithStream("A1 B!"));
    }

    @Test
    void lettersEmptyTest() {
        assertEquals("", LetterNumbers.formatWithString("123"));
        assertEquals("", LetterNumbers.formatWithStream("123"));
    }

    @Test
    void cipher1x1Test() {
        assertEquals("a", SquareCipher.encryptViaString("a"));
        assertEquals("a", SquareCipher.encryptViaStream("a"));
    }

    @Test
    void cipher2x2Test() {
        assertEquals("bdca", SquareCipher.encryptViaString("abcd"));
        assertEquals("bdca", SquareCipher.encryptViaStream("abcd"));
    }

    @Test
    void cipher3x3Test() {
        assertEquals("ficbagdeh", SquareCipher.encryptViaString("abcdefghi"));
        assertEquals("ficbagdeh", SquareCipher.encryptViaStream("abcdefghi"));
    }

    @Test
    void cipherNotSquareTest() {
        assertThrows(IllegalArgumentException.class, () -> SquareCipher.encryptViaString("abc"));
        assertThrows(IllegalArgumentException.class, () -> SquareCipher.encryptViaStream("abc"));
    }
}