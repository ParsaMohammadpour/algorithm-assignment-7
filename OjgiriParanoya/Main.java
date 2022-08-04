import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Character> map[] = new ArrayList[8];

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            for (int i = 0; i < 8; i++)
                map[i] = new ArrayList<>();
            map[0].add('a');
            map[0].add('b');
            map[0].add('c');
            map[1].add('d');
            map[1].add('e');
            map[1].add('f');
            map[2].add('g');
            map[2].add('h');
            map[2].add('i');
            map[3].add('j');
            map[3].add('k');
            map[3].add('l');
            map[4].add('m');
            map[4].add('n');
            map[4].add('o');
            map[5].add('p');
            map[5].add('q');
            map[5].add('r');
            map[5].add('s');
            map[6].add('t');
            map[6].add('u');
            map[6].add('v');
            map[7].add('w');
            map[7].add('x');
            map[7].add('y');
            map[7].add('z');
            String s = reader.readLine();
            findAllStrings(s,new ArrayList<>(), 0,0);
        }catch (Exception e){e.printStackTrace();}
    }

    public static void findAllStrings(String s, ArrayList<Character> answer, int vowvels, int silent){
//        System.out.println(answer.size() + "     $$      " + answer.toString() + "     Vowvels : " + vowvels + "     Silent : " + silent);
        if (s.length() == answer.size()){
            for (int i = 0; i < answer.size(); i++) {
                System.out.print(answer.get(i));
            }
            System.out.println();
            return;
        }
        if (s.charAt(answer.size()) == '1' || s.charAt(answer.size()) == '0'){
            answer.add(s.charAt(answer.size()));
            findAllStrings(s, answer, 0, 0);
            answer.remove(answer.size() - 1);
        }else {
            for (int i = 0; i < map[Character.digit(s.charAt(answer.size()), 10) - 2].size(); i++) {
                boolean isVowvels = isVowvels(map[Character.digit(s.charAt(answer.size()), 10) - 2].get(i));
                if (isVowvels && vowvels == 2)
                    continue;
                if ((!isVowvels) && silent == 2)
                    continue;
                answer.add(map[Character.digit(s.charAt(answer.size()), 10) - 2].get(i));
                if (isVowvels)
                    findAllStrings(s, answer, vowvels+1, 0);
                else
                    findAllStrings(s, answer, 0, silent+1);
                answer.remove(answer.size() - 1);
            }
        }
    }

    public static boolean isVowvels(char c){
        switch (c){
            case 'a':
            case 'o':
            case 'i':
            case 'y':
            case 'e':
            case 'u':return true;
            default:return false;
        }
    }
}
