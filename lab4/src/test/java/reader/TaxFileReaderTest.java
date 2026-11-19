package reader;

import entity.Income;
import exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaxFileReaderTest {
    private final TaxFileReader reader = new TaxFileReader();

    @TempDir
    Path tempDir;

    @Test
    void readsOnlyValidLines() throws IOException, InvalidDataException {
        String content = """
                # comment
                SALARY;Job;1000;Yandex;true

                GIFT;Gift;500;false
                BONUS;Unknown;1
                SALARY;Bad;abc;Yandex;true
                """;
        Path file = tempDir.resolve("data.txt");
        Files.writeString(file, content);

        List<Income> incomes = reader.read(file.toString());

        assertEquals(2, incomes.size());
        assertEquals("Job", incomes.get(0).getName());
        assertEquals("Gift", incomes.get(1).getName());
    }

    @Test
    void emptyFileGivesEmptyList() throws IOException, InvalidDataException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");

        assertEquals(0, reader.read(file.toString()).size());
    }

    @Test
    void missingFileThrows() {
        String path = tempDir.resolve("no_such_file.txt").toString();
        assertThrows(InvalidDataException.class, () -> reader.read(path));
    }
}