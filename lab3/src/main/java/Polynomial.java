public class Polynomial {
    private final Complex[] coef;

    public Polynomial(Complex[] coef) {
        this.coef = coef;
    }

    public Polynomial add(Polynomial other) {
        int len = Math.max(coef.length, other.coef.length);
        Complex[] res = new Complex[len];
        for (int i = 0; i < len; i++) {
            Complex a = new Complex(0, 0);
            Complex b = new Complex(0, 0);
            if (i < coef.length) {
                a = coef[i];
            }
            if (i < other.coef.length) {
                b = other.coef[i];
            }
            res[i] = a.add(b);
        }
        return new Polynomial(res);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < coef.length; i++) {
            if (i > 0) {
                s = s + " + ";
            }
            s = s + coef[i];
            if (i == 1) {
                s = s + "*x";
            } else if (i > 1) {
                s = s + "*x^" + i;
            }
        }
        return s;
    }
}