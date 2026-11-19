import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);

        System.out.println("Skolko polinomov m:");
        int m = scan.nextInt();

        ArrayList<Polynomial> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            System.out.println("Stepen polinoma " + (i + 1) + ":");
            int deg = scan.nextInt();

            Complex[] c = new Complex[deg + 1];
            System.out.println("Vvedite koefficienty (re im) ot x^0 do x^" + deg + ":");
            for (int j = 0; j <= deg; j++) {
                double re = scan.nextDouble();
                double im = scan.nextDouble();
                c[j] = new Complex(re, im);
            }
            list.add(new Polynomial(c));
        }

        System.out.println("Polinomy:");
        for (Polynomial p : list) {
            System.out.println(p);
        }

        Polynomial sum = new Polynomial(new Complex[]{new Complex(0, 0)});
        for (Polynomial p : list) {
            sum = sum.add(p);
        }
        System.out.println("Summa:");
        System.out.println(sum);
    }
}