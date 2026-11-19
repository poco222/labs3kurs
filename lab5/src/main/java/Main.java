import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Payment payment = new Payment("Anna");
        payment.addProduct("Bread", 45.5, 2);
        payment.addProduct("Milk", 80, 3);
        Payment.Product cheese = payment.addProduct("Cheese", 350, 1);

        System.out.println("Customer: " + payment.getCustomer());
        System.out.println("Products in purchase:");
        for (Payment.Product product : payment.getProducts()) {
            System.out.println(product);
        }

        System.out.println("Owner of cheese (via inner class): " + cheese.getCustomer());

        System.out.println("Sorted by cost (descending):");
        for (Payment.Product product : payment.getSortedByCost()) {
            System.out.println(product);
        }

        System.out.println(String.format(Locale.US, "Total: %.2f", payment.getTotal()));
    }
}