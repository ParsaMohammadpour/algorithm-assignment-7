import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static String[] sequence;
    static String[][] matrix;
    static boolean isFinished;
    static int m, n;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            m = Integer.parseInt(line[0]);
            n = Integer.parseInt(line[1]);
            matrix = new String[m][n];
            for (int i = 0; i < m; i++) {
                matrix[i] = reader.readLine().split(" ");
            }
            int q = Integer.parseInt(reader.readLine());
            boolean empty = true;
            for (int i = 0; i < q; i++) {
                line = reader.readLine().split(" ");
                sequence = new String[Integer.parseInt(line[0])];
                for (int j = 0; j < sequence.length; j++)
                    sequence[j] = line[j + 1];
                isFinished = false;
                findSequence(0, 0, 0);
                if (isFinished)
                    empty = false;
            }
            if (empty) {
                System.out.println("-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findSequence(int i, int j, int current) throws Exception {
        if (isFinished)
            return;
        if (current == sequence.length) {
            print();
            isFinished = true;
            return;
        }
        if (i == -1 || j == -1 || i == m || j == n)
            return;
        String s;
        if (current == 0) {
            for (int k = 0; k < m; k++) {
                for (int l = 0; l < n; l++) {
                    if (matrix[k][l].equals(sequence[current])) {
                        s = matrix[k][l];
                        matrix[k][l] = null;
                        findSequence(k + 1, l, 1);
                        if (isFinished) {
                            matrix[k][l] = s;
                            return;
                        }
                        findSequence(k, l + 1, 1);
                        if (isFinished) {
                            matrix[k][l] = s;
                            return;
                        }
                        findSequence(k - 1, l, 1);
                        if (isFinished) {
                            matrix[k][l] = s;
                            return;
                        }
                        findSequence(k, l - 1, 1);
                        matrix[k][l] = s;
                        if (isFinished)
                            return;
                    }
                }
            }
            return;
        }
        if (matrix[i][j] == null)
            return;
        if (!matrix[i][j].equals(sequence[current]))
            return;
        current++;
        s = matrix[i][j];
        matrix[i][j] = null;
        findSequence(i + 1, j, current);
        if (isFinished) {
            matrix[i][j] = s;
            return;
        }
        findSequence(i, j + 1, current);
        if (isFinished) {
            matrix[i][j] = s;
            return;
        }
        findSequence(i - 1, j, current);
        if (isFinished) {
            matrix[i][j] = s;
            return;
        }
        findSequence(i, j - 1, current);
        matrix[i][j] = s;
    }

    private static void print() throws Exception {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int k = 0; k < sequence.length; k++)
            writer.write(sequence[k] + " ");
        writer.write("\n");
        writer.flush();
    }
}