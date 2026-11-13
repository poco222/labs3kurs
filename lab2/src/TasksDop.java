import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TasksDop {
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
            int[] nums = new Random().ints(0, Math.max(300, n * n))
                    .distinct()
                    .limit(n * n)
                    .toArray();
            int idx = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = nums[idx];
                    idx++;
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

        System.out.println("Zadanie: 1 - sdvig, 2 - diagonali i udalenie strok:");
        int task = scan.nextInt();

        if (task == 1) {
            System.out.println("k:");
            int k = scan.nextInt();
            System.out.println("1 - vpravo, 2 - vlevo, 3 - vverh, 4 - vniz:");
            int dir = scan.nextInt();

            int[][] res;
            if (dir == 1) {
                res = IntStream.range(0, n)
                        .mapToObj(i -> IntStream.range(0, n)
                                .map(j -> a[i][(j - k % n + n) % n])
                                .toArray())
                        .toArray(int[][]::new);
            } else if (dir == 2) {
                res = IntStream.range(0, n)
                        .mapToObj(i -> IntStream.range(0, n)
                                .map(j -> a[i][(j + k) % n])
                                .toArray())
                        .toArray(int[][]::new);
            } else if (dir == 3) {
                res = IntStream.range(0, n)
                        .mapToObj(i -> IntStream.range(0, n)
                                .map(j -> a[(i + k) % n][j])
                                .toArray())
                        .toArray(int[][]::new);
            } else {
                res = IntStream.range(0, n)
                        .mapToObj(i -> IntStream.range(0, n)
                                .map(j -> a[(i - k % n + n) % n][j])
                                .toArray())
                        .toArray(int[][]::new);
            }

            System.out.println("Rezultat:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(res[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            int max = IntStream.range(0, n)
                    .flatMap(i -> IntStream.of(a[i][i], a[i][n - 1 - i]))
                    .max()
                    .getAsInt();
            System.out.println("Max na diagonalyah: " + max);

            System.out.println("Zadannoe chislo dvuznachnyh p:");
            int p = scan.nextInt();

            int[][] res = Arrays.stream(a)
                    .filter(row -> Arrays.stream(row)
                            .filter(x -> x >= 10 && x <= 99)
                            .count() != p)
                    .toArray(int[][]::new);

            if (res.length == 0) {
                System.out.println("Vse stroki udaleny");
            } else {
                System.out.println("Rezultat:");
                for (int i = 0; i < res.length; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(res[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}