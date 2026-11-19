import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {
    private static final double DELTA = 0.0001;

    private Payment samplePayment() {
        Payment payment = new Payment("Anna");
        payment.addProduct("Bread", 45.5, 2);
        payment.addProduct("Milk", 80, 3);
        payment.addProduct("Cheese", 350, 1);
        return payment;
    }

    @Test
    void emptyPaymentHasZeroTotal() {
        Payment payment = new Payment("Anna");
        assertEquals(0, payment.getTotal(), DELTA);
        assertEquals(0, payment.getProductsCount());
    }

    @Test
    void productCost() {
        Payment payment = new Payment("Anna");
        Payment.Product product = payment.addProduct("Milk", 80, 3);
        assertEquals(240, product.getCost(), DELTA);
        assertEquals("Milk", product.getName());
        assertEquals(3, product.getQuantity());
    }

    @Test
    void totalOfSeveralProducts() {
        Payment payment = samplePayment();
        assertEquals(3, payment.getProductsCount());
        assertEquals(681, payment.getTotal(), DELTA);
    }

    @Test
    void innerClassSeesOuterCustomer() {
        Payment payment = new Payment("Boris");
        Payment.Product product = payment.addProduct("Tea", 100, 1);
        assertEquals("Boris", product.getCustomer());
    }

    @Test
    void sortedByCostDescending() {
        List<Payment.Product> sorted = samplePayment().getSortedByCost();
        assertEquals("Cheese", sorted.get(0).getName());
        assertEquals("Milk", sorted.get(1).getName());
        assertEquals("Bread", sorted.get(2).getName());
    }

    @Test
    void sortingDoesNotChangeOriginalOrder() {
        Payment payment = samplePayment();
        payment.getSortedByCost();
        assertEquals("Bread", payment.getProducts().get(0).getName());
    }

    @Test
    void negativePriceThrows() {
        Payment payment = new Payment("Anna");
        assertThrows(IllegalArgumentException.class, () -> payment.addProduct("X", -1, 1));
    }

    @Test
    void zeroQuantityThrows() {
        Payment payment = new Payment("Anna");
        assertThrows(IllegalArgumentException.class, () -> payment.addProduct("X", 10, 0));
    }

    @Test
    void toStringFormat() {
        Payment payment = new Payment("Anna");
        Payment.Product product = payment.addProduct("Milk", 80, 3);
        assertEquals("Milk: 3 x 80.00 = 240.00", product.toString());
    }
}