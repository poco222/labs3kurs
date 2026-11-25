import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LetterNumbers {
    private static final String RUS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    // номер буквы в алфавите
    public static int letterNumber(char c) {
        char u = Character.toUpperCase(c);
        if (u >= 'A' && u <= 'Z') {
            return u - 'A' + 1;
        }
        return RUS.indexOf(u) + 1;
    }

    // способ 1: методы String
    public static String formatWithString(String text) {
        String only = text.replaceAll("[^A-Za-zА-Яа-яЁё]", "");
        if (only.isEmpty()) {
            return "";
        }
        StringBuilder top = new StringBuilder();
        StringBuilder bottom = new StringBuilder();
        for (char c : only.toCharArray()) {
            top.append(String.format("%-2s", c)).append(" ");
            bottom.append(String.format("%-2d", letterNumber(c))).append(" ");
        }
        return top.toString().stripTrailing() + "\n" + bottom.toString().stripTrailing();
    }

    // способ 2: Stream
    public static String formatWithStream(String text) {
        var letters = Pattern.compile("[A-Za-zА-Яа-яЁё]")
                .matcher(text)
                .results()
                .map(m -> m.group())
                .toList();
        if (letters.isEmpty()) {
            return "";
        }
        String top = letters.stream()
                .map(s -> String.format("%-2s", s))
                .collect(Collectors.joining(" "));
        String bottom = letters.stream()
                .map(s -> String.format("%-2d", letterNumber(s.charAt(0))))
                .collect(Collectors.joining(" "));
        return top.stripTrailing() + "\n" + bottom.stripTrailing();
    }
}