import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lab7Test {

    @Test
    void anagramTest() {
        assertTrue(Anagrams.own.check("listen", "silent"));
        assertTrue(Anagrams.std.test("Listen", "Silent"));
    }

    @Test
    void notAnagramTest() {
        assertFalse(Anagrams.own.check("hello", "world"));
        assertFalse(Anagrams.std.test("abc", "abcd"));
    }

    @Test
    void fibonacciTest() {
        for (long n : new long[]{0, 1, 2, 3, 5, 8, 13, 21}) {
            assertTrue(Fibonacci.own.test(n));
            assertTrue(Fibonacci.std.test(n));
        }
    }

    @Test
    void notFibonacciTest() {
        for (long n : new long[]{4, 6, 7, 10, 20}) {
            assertFalse(Fibonacci.own.test(n));
            assertFalse(Fibonacci.std.test(n));
        }
    }

    @Test
    void primesTest() {
        List<Long> expected = List.of(2L, 3L, 5L, 7L, 11L);
        assertEquals(expected, Primes.firstOwn(5));
        assertEquals(expected, Primes.firstStd(5));
    }

    @Test
    void primesZeroTest() {
        assertTrue(Primes.firstOwn(0).isEmpty());
        assertTrue(Primes.firstStd(0).isEmpty());
    }
}