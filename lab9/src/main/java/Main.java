import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String in = args.length > 0 ? args[0] : "input.txt";
        String out = args.length > 1 ? args[1] : "output.txt";

        String text = Files.readString(Path.of(in)).replace("\uFEFF", "");

        List<Sentence> sorted = TextTasks.sortByWordCount(TextParser.parse(text));

        List<String> lines = new ArrayList<>();
        for (Sentence s : sorted) {
            lines.add(s.toString());
        }
        Files.write(Path.of(out), lines);

        lines.forEach(System.out::println);
    }
}