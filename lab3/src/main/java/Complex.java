public class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    @Override
    public String toString() {
        if (im >= 0) {
            return "(" + re + " + " + im + "i)";
        }
        return "(" + re + " - " + (-im) + "i)";
    }
}