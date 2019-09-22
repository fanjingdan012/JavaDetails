package crypt;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SubstitutionTest {
    @Test
    public void testCesar() {
        String s = "FGNGVBAKJNFGURXRLXXXCESAR";
        for (int i = 0; i < 26; i++) {
            String cesar = Substitution.cesar(i, s);
            System.out.println(cesar);
        }
    }

    @Test
    public void testSubstituteWithMap(){
        String key = "DACyLQ8DppU";
        String em = "GZVURTKQBKUNKTIKEVQZNZOBKYUNMIJMTD MOKEMZUTAFZNOEFHMWVJFQBWQZNNYEEM KTWJVBMDPLOWPLRBKYLNNEVLZTFQEFQKTK KPEWWHDJTPNDCIAJK\n" +
                "YKMBODDRUMCZNMZTPFDJQUAAIKHZUPMW WYUAAUDTPDAAZENKTMKEBLZNLTILEVETPHZ TRYETZNMBKCAZVMDKZETFDJENODEKVDDPA JOR";
        char[] keyc = key.toCharArray();
        int i = 0;
        Map<Character, Character> m = new HashMap();
        m.put('A', 'Z');
        m.put('B', 'I');
        m.put('C', 'O');
        m.put('D', 'P');
        m.put('E', 'K');
        m.put('F', 'C');
        m.put('G', 'V');
        m.put('H', 'B');
        m.put('I', 'U');
        m.put('J', 'X');
        m.put('K', 'R');
        m.put('L', 'A');
        m.put('M', 'Y');
        m.put('N', 'T');
        m.put('O', 'D');
        m.put('P', 'H');
        m.put('Q','N');
        m.put('R', 'E');
        m.put('S', 'N');
        m.put('T', 'M');
        m.put('U', 'J');
        m.put('V', 'S');
        m.put('W', 'Q');
        m.put('X', 'G');
        m.put('Y', 'F');
        m.put('Z', 'W');
        Map<Character, Character> m2 = new HashMap<Character, Character>();
        for (Map.Entry<Character, Character> entry : m.entrySet()) {
            m2.put(entry.getValue(), entry.getKey());
        }
        assertEquals("",Substitution.substituteWithMap(em,m2));
    }

}