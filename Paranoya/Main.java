import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            String s = reader.readLine();
            Paranoya(s,0,s.length() - 1);
        }catch (Exception e){e.printStackTrace();}
    }

    public static void Paranoya(String s, int start, int end){
        if (end < start)
            return;
        if (end == start) {
            System.out.print(s.charAt(start));
            return;
        }
        int mid = (end + start) / 2;
        if ((end - start + 1) % 2 == 0){
            System.out.print(s.charAt(mid+1));
            System.out.print(s.charAt(mid));
            Paranoya(s,start, mid - 1);
            Paranoya(s,mid+2,end);
        }else {
            System.out.print(s.charAt(mid));
            Paranoya(s,start, mid - 1);
            Paranoya(s,mid+1,end);
        }
    }
}
