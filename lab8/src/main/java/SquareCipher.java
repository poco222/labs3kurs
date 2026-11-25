import java.util.Arrays;
import java.util.stream.IntStream;

public class SquareCipher {

    // проверка: текст не пустой и его длина равна n*n; возвращает n
    private static int size(String text) {
        if (text == null || !text.matches("(?s).+")) {
            throw new IllegalArgumentException("Пустой текст");
        }
        int n = (int) Math.sqrt(text.length());
        if (n * n != text.length()) {
            throw new IllegalArgumentException("Длина должна быть n*n, а сейчас " + text.length());
        }
        return n;
    }

    // способ 1: методы String и циклы
    public static String encryptViaString(String text) {
        int n = size(text);

        // 1. записываем в таблицу слева направо, сверху вниз
        char[][] t = new char[n][];
        for (int i = 0; i < n; i++) {
            t[i] = text.substring(i * n, (i + 1) * n).toCharArray();
        }

        // 2. поворот на 90° по часовой
        char[][] r = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r[j][n - 1 - i] = t[i][j];
            }
        }

        // 3. 1-я строка с последней, 2-я с предпоследней и т.д.
        for (int i = 0; i < n / 2; i++) {
            char[] tmp = r[i];
            r[i] = r[n - 1 - i];
            r[n - 1 - i] = tmp;
        }

        // 4. 1-й столбец со 2-м, 3-й с 4-м и т.д.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j + 1 < n; j += 2) {
                char tmp = r[i][j];
                r[i][j] = r[i][j + 1];
                r[i][j + 1] = tmp;
            }
        }

        // 5. обход по спирали
        return spiral(r);
    }

    // способ 2: Stream
    public static String encryptViaStream(String text) {
        int n = size(text);

        // 1. таблица
        char[][] t = IntStream.range(0, n)
                .mapToObj(i -> text.substring(i * n, (i + 1) * n).toCharArray())
                .toArray(char[][]::new);

        // 2. поворот
        char[][] r = new char[n][n];
        IntStream.range(0, n).forEach(i ->
                IntStream.range(0, n).forEach(j ->
                        r[j][n - 1 - i] = t[i][j]));

        // 3. строки в обратном порядке
        char[][] f = IntStream.range(0, n)
                .mapToObj(i -> r[n - 1 - i])
                .toArray(char[][]::new);

        // 4. столбцы парами
        char[][] g = Arrays.stream(f)
                .map(row -> {
                    char[] c = row.clone();
                    for (int j = 0; j + 1 < n; j += 2) {
                        char tmp = c[j];
                        c[j] = c[j + 1];
                        c[j + 1] = tmp;
                    }
                    return c;
                })
                .toArray(char[][]::new);

        // 5. спираль
        return spiral(g);
    }

    // обход по спирали по часовой стрелке с левого верхнего угла
    private static String spiral(char[][] a) {
        int n = a.length;
        StringBuilder sb = new StringBuilder();
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) sb.append(a[top][j]);
            top++;
            for (int i = top; i <= bottom; i++) sb.append(a[i][right]);
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) sb.append(a[bottom][j]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) sb.append(a[i][left]);
                left++;
            }
        }
        return sb.toString();
    }
}