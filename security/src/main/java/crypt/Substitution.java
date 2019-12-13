package crypt;

import java.util.HashMap;
import java.util.Map;

public class Substitution {

    public static String cesar(int offsset, String s) {
        char[] cs = s.toCharArray();
        for (int j = 0; j < cs.length; j++) {
            if('a'<=cs[j]&&cs[j]<='z'){
                cs[j] += offsset;
                if (cs[j] > 'z') {
                    cs[j] -= 26;
                }
            }
            else if('A'<=cs[j]&&cs[j]<='Z'){
                cs[j] += offsset;
                if (cs[j] > 'Z') {
                    cs[j] -= 26;
                }
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

    public static String decodeBacon(String bacon){

        bacon = bacon.toUpperCase();
        Map<String, String> map = new HashMap<>();
        map.put("AAAAA","a");
        map.put("AAAAB","b");
        map.put("AAABA","c");
        map.put("AAABB","d");
        map.put("AABAA","e");
        map.put("AABAB","f");
        map.put("AABBA","g");
        map.put("AABBB","h");
        map.put("ABAAA","i/j");
        map.put("ABAAB","k");
        map.put("ABABA","l");
        map.put("ABABB","m");
        map.put("ABBAA","n");
        map.put("ABBAB","o");
        map.put("ABBBA","p");
        map.put("ABBBB","q");
        map.put("BAAAA","r");
        map.put("BAAAB","s");
        map.put("BAABA","t");
        map.put("BAABB","u/v");
        map.put("BABAA","w");
        map.put("BABAB","x");
        map.put("BABBA","y");
        map.put("BABBB","z");
        System.out.println(bacon.length());
        StringBuilder builder  = new StringBuilder();

        for(int i = 0;i<bacon.length()/5;i++){
//            System.out.print(bacon.substring(i*5,i*5+5));
            builder.append(map.get(bacon.substring(i*5,i*5+5)));
        }

        return new String(builder);

    }


}
