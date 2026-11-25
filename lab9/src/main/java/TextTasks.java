import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextTasks {

    public static List<Sentence> sortByWordCount(List<Sentence> sentences) {
        List<Sentence> result = new ArrayList<>(sentences);
        result.sort(Comparator.comparingInt(Sentence::getWordCount));
        return result;
    }
}