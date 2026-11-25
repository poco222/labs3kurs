import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Primes {

    // свой интерфейс-генератор: каждый вызов next() даёт следующее значение
    @FunctionalInterface
    interface Generator<T> {
        T next();
    }

    static boolean isPrime(long x) {
        if (x < 2) {
            return false;
        }
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // способ 1: свой генератор
    static Generator<Long> primeGenerator() {
        long[] current = {1};
        return () -> {
            do {
                current[0]++;
            } while (!isPrime(current[0]));
            return current[0];
        };
    }

    public static List<Long> firstOwn(int n) {
        Generator<Long> gen = primeGenerator();
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(gen.next());
        }
        return result;
    }

    // способ 2: готовый Stream.iterate (бесконечный поток чисел)
    public static List<Long> firstStd(int n) {
        return Stream.iterate(2L, x -> x + 1)
                .filter(Primes::isPrime)
                .limit(n)
                .toList();
    }
}