package comparator;

import entity.TaxPayment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaxAmountComparatorTest {
    private final TaxAmountComparator comparator = new TaxAmountComparator();

    private List<TaxPayment> samplePayments() {
        return new ArrayList<>(List.of(
                new TaxPayment("a", "T", 300),
                new TaxPayment("b", "T", -50),
                new TaxPayment("c", "T", 200)));
    }

    @Test
    void sortsAscending() {
        List<TaxPayment> payments = samplePayments();
        payments.sort(comparator);
        assertEquals("b", payments.get(0).getIncomeName());
        assertEquals("c", payments.get(1).getIncomeName());
        assertEquals("a", payments.get(2).getIncomeName());
    }

    @Test
    void sortsDescendingWithReversed() {
        List<TaxPayment> payments = samplePayments();
        payments.sort(comparator.reversed());
        assertEquals("a", payments.get(0).getIncomeName());
        assertEquals("b", payments.get(2).getIncomeName());
    }

    @Test
    void compareResults() {
        TaxPayment small = new TaxPayment("s", "T", 1);
        TaxPayment big = new TaxPayment("b", "T", 2);
        assertTrue(comparator.compare(small, big) < 0);
        assertTrue(comparator.compare(big, small) > 0);
        assertEquals(0, comparator.compare(small, new TaxPayment("x", "T", 1)));
    }
}