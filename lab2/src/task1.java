import java.util.Random;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Razmer matricy n:");
        int n = scan.nextInt();
        int[][] a = new int[n][n];

        System.out.println("1 - vvod s konsoli, 2 - sluchajnye chisla:");
        int mode = scan.nextInt();

        if (mode == 1) {
            System.out.println("Vvedite elementy:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = scan.nextInt();
                }
            }
        } else {
            Random rnd = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = rnd.nextInt(100);
                }
            }
        }

        System.out.println("Ishodnaya matrica:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("k:");
        int k = scan.nextInt();
        System.out.println("1 - vpravo, 2 - vlevo, 3 - vverh, 4 - vniz:");
        int dir = scan.nextInt();

        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dir == 1) {
                    res[i][(j + k) % n] = a[i][j];
                } else if (dir == 2) {
                    res[i][(j - k % n + n) % n] = a[i][j];
                } else if (dir == 3) {
                    res[(i - k % n + n) % n][j] = a[i][j];
                } else {
                    res[(i + k) % n][j] = a[i][j];
                }
            }
        }

        System.out.println("Rezultat:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}