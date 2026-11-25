import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    // предложение: всё до .!? и сами знаки конца
    private static final Pattern SENTENCE = Pattern.compile("[^.!?]+[.!?]*");
    // лексема: слово (буквы и цифры) или один знак препинания
    private static final Pattern LEXEME = Pattern.compile("[\\p{L}\\p{N}]+|[^\\p{L}\\p{N}\\s]");

    public static List<Sentence> parse(String text) {
        // табуляции, переносы и повторы пробелов -> один пробел
        String clean = text.replaceAll("\\s+", " ").trim();

        List<Sentence> result = new ArrayList<>();
        Matcher m = SENTENCE.matcher(clean);
        while (m.find()) {
            String part = m.group().trim();
            if (!part.isEmpty()) {
                result.add(parseSentence(part));
            }
        }
        return result;
    }

    private static Sentence parseSentence(String text) {
        Sentence sentence = new Sentence();
        Matcher m = LEXEME.matcher(text);
        while (m.find()) {
            String t = m.group();
            if (Character.isLetterOrDigit(t.charAt(0))) {
                sentence.add(new Word(t));
            } else {
                sentence.add(new Punctuation(t.charAt(0)));
            }
        }
        return sentence;
    }
}