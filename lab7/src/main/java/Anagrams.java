import java.util.Arrays;
import java.util.function.BiPredicate;

public class Anagrams {

    @FunctionalInterface
    interface AnagramChecker {
        boolean check(String a, String b);
    }

    // буквы слова по алфавиту: "listen" -> "eilnst"
    static String sorted(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    // способ 1: свой интерфейс
    public static final AnagramChecker own =
            (a, b) -> sorted(a).equals(sorted(b));

    // способ 2: готовый BiPredicate (два аргумента, ответ boolean)
    public static final BiPredicate<String, String> std =
            (a, b) -> sorted(a).equals(sorted(b));
}