import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {

    @Test
    void addSameLength() {
        Polynomial p1 = new Polynomial(new Complex[]{new Complex(1, 0), new Complex(2, 0)});
        Polynomial p2 = new Polynomial(new Complex[]{new Complex(3, 1), new Complex(4, 0)});
        Polynomial s = p1.add(p2);
        assertEquals(2, s.size());
        assertEquals(4.0, s.get(0).getRe(), 0.0001);
        assertEquals(1.0, s.get(0).getIm(), 0.0001);
        assertEquals(6.0, s.get(1).getRe(), 0.0001);
    }

    @Test
    void addDifferentLength() {
        Polynomial p1 = new Polynomial(new Complex[]{new Complex(1, 2)});
        Polynomial p2 = new Polynomial(new Complex[]{
                new Complex(2, -1), new Complex(1, 1), new Complex(4, 0)});
        Polynomial s = p1.add(p2);
        assertEquals(3, s.size());
        assertEquals(3.0, s.get(0).getRe(), 0.0001);
        assertEquals(1.0, s.get(0).getIm(), 0.0001);
        assertEquals(1.0, s.get(1).getRe(), 0.0001);
        assertEquals(4.0, s.get(2).getRe(), 0.0001);
    }

    @Test
    void addZeroPolynomial() {
        Polynomial zero = new Polynomial(new Complex[]{new Complex(0, 0)});
        Polynomial p = new Polynomial(new Complex[]{new Complex(5, 5), new Complex(1, 0)});
        Polynomial s = zero.add(p);
        assertEquals(2, s.size());
        assertEquals(5.0, s.get(0).getRe(), 0.0001);
        assertEquals(5.0, s.get(0).getIm(), 0.0001);
    }

    @Test
    void toStringTest() {
        Polynomial p = new Polynomial(new Complex[]{new Complex(1, 0), new Complex(2, 1)});
        assertEquals("(1.0 + 0.0i) + (2.0 + 1.0i)*x", p.toString());
    }
}