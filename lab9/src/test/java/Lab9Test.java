import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lab9Test {

    @Test
    void spacesTest() {
        List<Sentence> s = TextParser.parse("Раз   два\tтри.");
        assertEquals(1, s.size());
        assertEquals("Раз два три.", s.get(0).toString());
    }

    @Test
    void wordCountTest() {
        List<Sentence> s = TextParser.parse("Привет, мир!");
        assertEquals(2, s.get(0).getWordCount());
    }

    @Test
    void sentencesCountTest() {
        assertEquals(3, TextParser.parse("Один. Два! Три?").size());
    }

    @Test
    void sortTest() {
        List<Sentence> sorted = TextTasks.sortByWordCount(
                TextParser.parse("Один два три. Раз. Привет, мир!"));
        assertEquals("Раз.", sorted.get(0).toString());
        assertEquals("Привет, мир!", sorted.get(1).toString());
        assertEquals("Один два три.", sorted.get(2).toString());
    }
}