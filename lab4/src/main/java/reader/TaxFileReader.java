package reader;

import creator.IncomeCreator;
import creator.IncomeFactory;
import entity.Income;
import exception.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.IncomeValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaxFileReader {
    private static final Logger LOGGER = LogManager.getLogger(TaxFileReader.class);
    private static final String SEPARATOR = ";";
    private static final String COMMENT = "#";
    private static final String BOM = "\uFEFF";

    private final IncomeValidator validator = new IncomeValidator();

    public List<Income> read(String path) throws InvalidDataException {
        LOGGER.info("Reading file: {}", path);
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Cannot read file {}", path, e);
            throw new InvalidDataException("Cannot read file: " + path, e);
        }

        List<Income> incomes = new ArrayList<>();
        for (String line : lines) {
            String clean = line.replace(BOM, "").trim();
            if (clean.isEmpty() || clean.startsWith(COMMENT)) {
                continue;
            }
            Income income = parseLine(clean);
            if (income != null) {
                incomes.add(income);
            }
        }
        LOGGER.info("Incomes loaded: {}", incomes.size());
        return incomes;
    }

    private Income parseLine(String line) {
        String[] parts = line.split(SEPARATOR, -1);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        if (!validator.isValid(parts)) {
            LOGGER.warn("Line skipped: {}", line);
            return null;
        }
        try {
            IncomeCreator creator = IncomeFactory.getCreator(parts[0]);
            Income income = creator.create(parts);
            LOGGER.debug("Created: {}", income);
            return income;
        } catch (InvalidDataException e) {
            LOGGER.warn("Line skipped: {} ({})", line, e.getMessage());
            return null;
        }
    }
}