import java.util.Scanner;

public class task3 {

    static int digitSum(long x) {
        int sum = 0;
        while (x > 0) {
            sum += (int) (x % 10);
            x = x / 10;
        }
        return sum;
    }

    static long reverse(long x) {
        long rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return rev;
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

        for (int i = 0; i < k; i++) {
            long n = numbers[i];
            if (digitSum(n) % 2 == 0) {
                System.out.println(n + " -> " + reverse(n));
            }
        }
    }
}