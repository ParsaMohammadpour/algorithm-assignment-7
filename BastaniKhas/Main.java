import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static long answer = 0;
    static long[] r;
    static long minDiff;
    static long max;
    static long min;
    static int number;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] line = reader.readLine().split(" ");
            number = Integer.parseInt(line[0]);
            min = Long.parseLong(line[1]);
            max = Long.parseLong(line[2]);
            minDiff = Long.parseLong(line[3]);
            line = reader.readLine().split(" ");
            r = new long[number];
            for (int i = 0; i < line.length; i++)
                r[i] = Long.parseLong(line[i]);
            findPossibleWay(0,0,Long.MAX_VALUE, Long.MIN_VALUE,0);
            writer.write(answer+"\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findPossibleWay(int counter, long sum, long minChosen, long maxChosen, int taken) {
        if (counter == number) {
            if ((taken >= 2 ) && (maxChosen - minChosen >= minDiff) && (sum >= min && sum<=max))
                answer++;
            return;
        }
        findPossibleWay(counter+1, sum, minChosen, maxChosen, taken);
        if (sum + r[counter] <= max)
            findPossibleWay(counter + 1, sum + r[counter], Long.min(minChosen,r[counter])
                    ,Long.max(maxChosen,r[counter]), taken + 1);
    }
}
