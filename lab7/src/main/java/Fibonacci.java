import java.util.function.Predicate;

public class Fibonacci {

    @FunctionalInterface
    interface NumberCheck {
        boolean test(long n);
    }

    // способ 1: свой интерфейс
    public static final NumberCheck own = n -> {
        long a = 0;
        long b = 1;
        while (a < n) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a == n;
    };

    // способ 2: готовый Predicate
    public static final Predicate<Long> std = n -> {
        long a = 0;
        long b = 1;
        while (a < n) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a == n;
    };
}