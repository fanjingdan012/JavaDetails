package crypt;

import java.util.Map;

public class Substitution {

    public static String cesar(int offsset, String s) {
        char[] cs = s.toCharArray();
        for (int j = 0; j < cs.length; j++) {
            cs[j] += offsset;
            if (cs[j] > 'Z') {
                cs[j] -= 26;
            }
        }
        return new String(cs);

    }

    //https://crypto-tasks.livejournal.com/28226.html
    public static String substituteWithMap(String s, Map<Character, Character> charMap) {
        char[] chars = s.toCharArray();

        char[] resultChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            //resultChars[i] = charMap.get(chars[i]);
            System.out.print(charMap.get(chars[i]));
        }
//        return new String(resultChars);
        return "";

    }


}
