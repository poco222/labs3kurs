package validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeValidatorTest {
    private final IncomeValidator validator = new IncomeValidator();

    private boolean check(String line) {
        return validator.isValid(line.split(";", -1));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "SALARY;Job;1000;Yandex;true",
            "AUTHOR;Book;5000;20",
            "PROPERTY;Flat;5000;3000;2",
            "GIFT;Money;100;false",
            "FOREIGN;Transfer;100;Germany",
            "AID;Aid;100;Boss",
            "CHILD;Child;16800;2",
            "salary;Job;1000;Yandex;TRUE"
    })
    void validLines(String line) {
        assertTrue(check(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "SALARY;Broken;abc;Yandex;true",
            "SALARY;Negative;-500;Yandex;true",
            "SALARY;Infinite;Infinity;Yandex;true",
            "BONUS;Unknown;1000;x",
            "AUTHOR;High percent;50000;150",
            "AUTHOR;Bad percent;50000;abc",
            "GIFT;Missing field;1000",
            "PROPERTY;Bad years;100000;50000;two",
            "PROPERTY;Negative years;100000;50000;-1",
            "PROPERTY;Bad price;100000;abc;2",
            "FOREIGN;;1000;USA",
            "FOREIGN;Transfer;1000;",
            "CHILD;Zero children;16800;0",
            "SALARY;Bad boolean;1000;Yandex;maybe",
            "SALARY;Empty employer;1000;;true"
    })
    void invalidLines(String line) {
        assertFalse(check(line));
    }

    @Test
    void nullAndShortArraysAreInvalid() {
        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(new String[]{"SALARY", "Job"}));
    }
}