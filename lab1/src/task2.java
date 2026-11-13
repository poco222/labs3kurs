import java.util.Scanner;
public class task2 {

    static int digitSum(long x) {
        int sum = 0;
        while (x > 0) {
            sum += (int) (x % 10);
            x = x / 10;
        }
        return sum;
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vvedite kolichestvo el");
        int k = scan.nextInt();
        long[] numbers = new long[k];
        System.out.println("Daj chisla");
        for (int i = 0; i < k; i++) {
            numbers[i] = scan.nextLong();
        }

        long best = -1;
        int bestSum = -1;
        for (int i = 0; i < k; i++) {
            long n = numbers[i];
            if (isPrime(n)) {
                int s = digitSum(n);
                if (s > bestSum) {
                    bestSum = s;
                    best = n;
                }
            }
        }

        if (bestSum == -1) {
            System.out.println("Prostyh chisel net");
        } else {
            System.out.println("Otvet: " + best + " (summa cifr " + bestSum + ")");
        }
    }
}