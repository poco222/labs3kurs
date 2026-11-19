package entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeTest {
    private static final double DELTA = 0.0001;

    @Test
    void salaryGetters() {
        SalaryIncome income = new SalaryIncome("Job", 1000, "Yandex", true);
        assertEquals("Job", income.getName());
        assertEquals(1000, income.getAmount(), DELTA);
        assertEquals("Yandex", income.getEmployer());
        assertTrue(income.isMainJob());
    }

    @Test
    void otherGetters() {
        assertEquals(20, new AuthorFeeIncome("A", 1, 20).getDeductionPercent(), DELTA);
        PropertySaleIncome property = new PropertySaleIncome("P", 10, 5, 3);
        assertEquals(5, property.getPurchasePrice(), DELTA);
        assertEquals(3, property.getOwnedYears());
        assertFalse(new GiftIncome("G", 1, false).isFromCloseRelative());
        assertEquals("USA", new ForeignTransferIncome("F", 1, "USA").getCountry());
        assertEquals("Boss", new MaterialAidIncome("M", 1, "Boss").getProvider());
        assertEquals(2, new ChildBenefit("C", 1, 2).getChildrenCount());
    }

    @Test
    void toStringContainsClassName() {
        List<Income> incomes = List.of(
                new SalaryIncome("N", 1, "E", true),
                new AuthorFeeIncome("N", 1, 10),
                new PropertySaleIncome("N", 1, 1, 1),
                new GiftIncome("N", 1, true),
                new ForeignTransferIncome("N", 1, "C"),
                new MaterialAidIncome("N", 1, "P"),
                new ChildBenefit("N", 1, 1));
        for (Income income : incomes) {
            assertTrue(income.toString().contains(income.getClass().getSimpleName()));
        }
    }

    @Test
    void taxPaymentToString() {
        TaxPayment payment = new TaxPayment("Job", "SalaryIncome", 13000);
        assertEquals("Job", payment.getIncomeName());
        assertEquals("SalaryIncome", payment.getIncomeType());
        assertEquals("Job [SalaryIncome]: 13000.00", payment.toString());
    }
}