import java.util.ArrayList;
import java.util.List;

public class Word implements Lexeme {
    private final List<Symbol> symbols = new ArrayList<>();

    public Word(String text) {
        for (char c : text.toCharArray()) {
            symbols.add(new Symbol(c));
        }
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Symbol s : symbols) {
            sb.append(s.getValue());
        }
        return sb.toString();
    }
}