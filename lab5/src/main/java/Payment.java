import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Payment {
    private final String customer;
    private final List<Product> products = new ArrayList<>();

    public Payment(String customer) {
        this.customer = customer;
    }

    public Product addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        products.add(product);
        return product;
    }

    public String getCustomer() {
        return customer;
    }

    public int getProductsCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getCost();
        }
        return total;
    }

    public List<Product> getSortedByCost() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new Comparator<Product>() {
            @Override
            public int compare(Product first, Product second) {
                return Double.compare(second.getCost(), first.getCost());
            }
        });
        return sorted;
    }

    public class Product {
        private final String name;
        private final double price;
        private final int quantity;

        private Product(String name, double price, int quantity) {
            if (price < 0) {
                throw new IllegalArgumentException("Price must not be negative");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getCost() {
            return price * quantity;
        }

        public String getCustomer() {
            return Payment.this.customer;
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%s: %d x %.2f = %.2f", name, quantity, price, getCost());
        }
    }
}