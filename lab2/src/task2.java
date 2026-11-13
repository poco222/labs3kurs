import java.util.Random;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Razmer matricy n (n*n <= 300):");
        int n = scan.nextInt();
        int[][] a = new int[n][n];

        System.out.println("1 - vvod s konsoli, 2 - sluchajnye chisla:");
        int mode = scan.nextInt();

        if (mode == 1) {
            System.out.println("Vvedite elementy (razlichnye):");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = scan.nextInt();
                }
            }
        } else {
            Random rnd = new Random();
            boolean[] used = new boolean[300];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int x;
                    do {
                        x = rnd.nextInt(300);
                    } while (used[x]);
                    used[x] = true;
                    a[i][j] = x;
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

        int max = a[0][0];
        for (int i = 0; i < n; i++) {
            if (a[i][i] > max) {
                max = a[i][i];
            }
            if (a[i][n - 1 - i] > max) {
                max = a[i][n - 1 - i];
            }
        }
        System.out.println("Max na diagonalyah: " + max);

        System.out.println("Zadannoe chislo dvuznachnyh p:");
        int p = scan.nextInt();

        int[] cnt = new int[n];
        int left = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] >= 10 && a[i][j] <= 99) {
                    cnt[i]++;
                }
            }
            if (cnt[i] != p) {
                left++;
            }
        }

        if (left == 0) {
            System.out.println("Vse stroki udaleny");
        } else {
            int[][] res = new int[left][n];
            int row = 0;
            for (int i = 0; i < n; i++) {
                if (cnt[i] != p) {
                    for (int j = 0; j < n; j++) {
                        res[row][j] = a[i][j];
                    }
                    row++;
                }
            }

            System.out.println("Rezultat:");
            for (int i = 0; i < left; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(res[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}