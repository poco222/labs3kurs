package service;

import entity.AuthorFeeIncome;
import entity.ChildBenefit;
import entity.ForeignTransferIncome;
import entity.GiftIncome;
import entity.Income;
import entity.MaterialAidIncome;
import entity.PropertySaleIncome;
import entity.SalaryIncome;
import entity.TaxPayment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaxCalculatorTest {
    private static final double DELTA = 0.0001;
    private final TaxCalculator calculator = new TaxCalculator();

    private double tax(Income income) {
        return calculator.calculate(income).getTaxAmount();
    }

    @Test
    void salaryTax() {
        assertEquals(13000, tax(new SalaryIncome("Job", 100000, "Yandex", true)), DELTA);
    }

    @Test
    void authorFeeTaxWithDeduction() {
        assertEquals(10400, tax(new AuthorFeeIncome("Book", 100000, 20)), DELTA);
    }

    @Test
    void propertyOwnedLongIsFree() {
        assertEquals(0, tax(new PropertySaleIncome("Flat", 5000000, 3000000, 5)), DELTA);
    }

    @Test
    void propertyOwnedShortIsTaxedFromProfit() {
        assertEquals(260000, tax(new PropertySaleIncome("Flat", 5000000, 3000000, 2)), DELTA);
    }

    @Test
    void propertySoldWithLossHasNoTax() {
        assertEquals(0, tax(new PropertySaleIncome("Car", 500000, 700000, 1)), DELTA);
    }

    @Test
    void giftFromRelativeIsFree() {
        assertEquals(0, tax(new GiftIncome("Gift", 100000, true)), DELTA);
    }

    @Test
    void giftFromStrangerIsTaxed() {
        assertEquals(13000, tax(new GiftIncome("Gift", 100000, false)), DELTA);
    }

    @Test
    void foreignTransferTax() {
        assertEquals(13000, tax(new ForeignTransferIncome("Transfer", 100000, "Germany")), DELTA);
    }

    @Test
    void aidBelowLimitIsFree() {
        assertEquals(0, tax(new MaterialAidIncome("Aid", 3000, "Boss")), DELTA);
    }

    @Test
    void aidAboveLimitTaxedOnlyAboveLimit() {
        assertEquals(780, tax(new MaterialAidIncome("Aid", 10000, "Boss")), DELTA);
    }

    @Test
    void childBenefitReducesTax() {
        assertEquals(-4368, tax(new ChildBenefit("Child", 16800, 2)), DELTA);
    }

    @Test
    void paymentKeepsNameAndType() {
        TaxPayment payment = calculator.calculate(new SalaryIncome("Job", 1000, "Yandex", true));
        assertEquals("Job", payment.getIncomeName());
        assertEquals("SalaryIncome", payment.getIncomeType());
    }

    @Test
    void calculateAllAndTotal() {
        List<Income> incomes = List.of(
                new SalaryIncome("Job", 100000, "Yandex", true),
                new GiftIncome("Gift", 100000, false),
                new ChildBenefit("Child", 16800, 2));

        List<TaxPayment> payments = calculator.calculateAll(incomes);

        assertEquals(3, payments.size());
        assertEquals(21632, calculator.getTotal(payments), DELTA);
    }

    @Test
    void emptyListGivesZeroTotal() {
        assertEquals(0, calculator.getTotal(List.of()), DELTA);
    }

    @Test
    void unknownIncomeThrows() {
        Income unknown = new Income("Unknown", 1) {
        };
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(unknown));
    }
}