package validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public class IncomeValidator {
    private static final Logger LOGGER = LogManager.getLogger(IncomeValidator.class);
    private static final int BASE_FIELDS = 3;
    private static final double MAX_PERCENT = 100;
    private static final Map<String, Integer> FIELDS_COUNT = Map.of(
            "SALARY", 5,
            "AUTHOR", 4,
            "PROPERTY", 5,
            "GIFT", 4,
            "FOREIGN", 4,
            "AID", 4,
            "CHILD", 4);

    public boolean isValid(String[] parts) {
        if (parts == null || parts.length < BASE_FIELDS) {
            LOGGER.warn("Too few fields: {}", Arrays.toString(parts));
            return false;
        }
        String type = parts[0].toUpperCase(Locale.ROOT);
        Integer expected = FIELDS_COUNT.get(type);
        if (expected == null) {
            LOGGER.warn("Unknown income type: {}", parts[0]);
            return false;
        }
        if (parts.length != expected) {
            LOGGER.warn("Wrong fields count for {}: expected {}, got {}", type, expected, parts.length);
            return false;
        }
        if (parts[1].isBlank()) {
            LOGGER.warn("Empty name: {}", Arrays.toString(parts));
            return false;
        }
        if (!isNonNegativeNumber(parts[2])) {
            LOGGER.warn("Wrong amount: {}", parts[2]);
            return false;
        }
        if (!hasValidExtraFields(type, parts)) {
            LOGGER.warn("Wrong extra fields: {}", Arrays.toString(parts));
            return false;
        }
        return true;
    }

    private boolean hasValidExtraFields(String type, String[] parts) {
        return switch (type) {
            case "SALARY" -> !parts[3].isBlank() && isBoolean(parts[4]);
            case "AUTHOR" -> isPercent(parts[3]);
            case "PROPERTY" -> isNonNegativeNumber(parts[3]) && isInteger(parts[4], 0);
            case "GIFT" -> isBoolean(parts[3]);
            case "FOREIGN", "AID" -> !parts[3].isBlank();
            case "CHILD" -> isInteger(parts[3], 1);
            default -> false;
        };
    }

    private boolean isNonNegativeNumber(String value) {
        try {
            double number = Double.parseDouble(value);
            return Double.isFinite(number) && number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isPercent(String value) {
        return isNonNegativeNumber(value) && Double.parseDouble(value) <= MAX_PERCENT;
    }

    private boolean isInteger(String value, int min) {
        try {
            return Integer.parseInt(value) >= min;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }
}