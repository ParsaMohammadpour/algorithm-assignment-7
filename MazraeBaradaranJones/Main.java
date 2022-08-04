import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int number;
    static long[] array;
    static long[][] table;
    static long sum = 0;
    static long[] soton;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            number = Integer.parseInt(reader.readLine());
            int numberPow2 = number * number;
            String[] line = reader.readLine().split(" ");
            array = new long[numberPow2];
            soton = new long[number];
            for (int i = 0; i < line.length; i++) {
                array[i] = Long.parseLong(line[i]);
                sum += array[i];
            }
            sum /= number;
            table = new long[number][number];
            findTable(0, 0, 0, array.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findTable(int i, int j, long currentSum, int last) throws Exception {
        if (i == number) {
            long s=0, s1=0;
            for (int k = 0; k < number; k++) {
                s += table[k][k];
                s1 += table[k][number - 1 - k];
            }
            if (s!=sum || s1!=sum)
                return;
            print();
        }
        for (int k = 0; k < last; k++) {
            if (j == number - 1) {
                if (currentSum + array[k] != sum)
                    continue;
            }
            if (i == number - 1){
                if (soton[j] + array[k] != sum)
                    continue;
            }
            long temp = array[k];
            array[k] = array[last - 1];
            table[i][j] = temp;
            int temp1 = i;
            int temp2 = j + 1;
            if (temp2 == number) {
                temp2 = 0;
                temp1++;
                currentSum = -temp;
            }
            soton[j] += temp;
            findTable(temp1, temp2, currentSum + temp, last - 1);
            soton[j] -= temp;
            array[k] = temp;
        }
    }

    private static void print() throws Exception {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(sum + "\n");
        writer.flush();
        for (int m = 0; m < table.length; m++) {
            for (int n = 0; n < table[m].length; n++) {
                writer.write(table[m][n] + " ");
            }
            writer.write("\n");
            writer.flush();
        }
        System.exit(0);
    }
}
