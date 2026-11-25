import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Lexeme> lexemes = new ArrayList<>();

    public void add(Lexeme lexeme) {
        lexemes.add(lexeme);
    }

    public int getWordCount() {
        int count = 0;
        for (Lexeme l : lexemes) {
            if (l instanceof Word) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lexeme l : lexemes) {
            // перед словом ставим пробел, знак препинания клеим к предыдущему
            if (l instanceof Word && sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(l.getText());
        }
        return sb.toString();
    }
}