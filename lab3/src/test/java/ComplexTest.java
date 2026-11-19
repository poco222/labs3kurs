import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexTest {

    @Test
    void addTest() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, -5);
        Complex c = a.add(b);
        assertEquals(4.0, c.getRe(), 0.0001);
        assertEquals(-3.0, c.getIm(), 0.0001);
    }

    @Test
    void addDoesNotChangeOriginal() {
        Complex a = new Complex(1, 2);
        a.add(new Complex(10, 10));
        assertEquals(1.0, a.getRe(), 0.0001);
        assertEquals(2.0, a.getIm(), 0.0001);
    }

    @Test
    void toStringPositiveIm() {
        assertEquals("(1.0 + 2.0i)", new Complex(1, 2).toString());
    }

    @Test
    void toStringNegativeIm() {
        assertEquals("(1.0 - 2.0i)", new Complex(1, -2).toString());
    }
}