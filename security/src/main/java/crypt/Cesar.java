package crypt;

public class Cesar {

     public static void main(String[] arg) {
        String s = "FGNGVBAKJNFGURXRLXXXCESAR";
        char[] cs = s.toCharArray();
        for (int i = 0; i < 26; i++) {
            for(int j = 0;j<cs.length;j++){
                cs[j]++;
                if(cs[j]>'Z'){
                    cs[j]-=26;
                }
                System.out.print(cs[j]);
            }
            System.out.println();
        }
    }
}
