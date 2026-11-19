package app;

import comparator.TaxAmountComparator;
import entity.Income;
import entity.TaxPayment;
import exception.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reader.TaxFileReader;
import service.TaxCalculator;

import java.util.List;
import java.util.Locale;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String DATA_FILE = "data/data.txt";

    public static void main(String[] args) {
        LOGGER.info("Program started");
        try {
            TaxFileReader reader = new TaxFileReader();
            List<Income> incomes = reader.read(DATA_FILE);

            TaxCalculator calculator = new TaxCalculator();
            List<TaxPayment> payments = calculator.calculateAll(incomes);
            payments.sort(new TaxAmountComparator());

            LOGGER.info("Taxes sorted by amount (ascending):");
            for (TaxPayment payment : payments) {
                LOGGER.info("{}", payment);
            }
            double total = calculator.getTotal(payments);
            LOGGER.info("Total tax: {}", String.format(Locale.US, "%.2f", total));
        } catch (InvalidDataException e) {
            LOGGER.fatal("Program stopped: {}", e.getMessage());
        }
        LOGGER.info("Program finished");
    }
}