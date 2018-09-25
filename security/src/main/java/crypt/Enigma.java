package crypt;

import java.util.*;

import static crypt.Rotor.WHEEL_I;
import static crypt.Rotor.WHEEL_II;
import static crypt.Rotor.WHEEL_III;

public class Enigma {
    //https://www.codeproject.com/Articles/831015/ENIGMA
    //http://www.cryptomuseum.com/crypto/enigma/working.htm
    //https://en.wikipedia.org/wiki/Enigma_machine#Turnover
    //https://en.wikipedia.org/wiki/Enigma_rotor_details#Ring_setting
    //https://www.codesandciphers.org.uk/enigma/rotorspec.htm
    //https://www.codesandciphers.org.uk/enigma/example1.htm
    //https://www.jianshu.com/p/8655ef53963d
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";



    //        Wheel          Wiring              Notch   Turnover
    //         No.   ABCDEFGHIJKLMNOPQRSTUVWXYZ

    //          I    EKMFLGDQVZNTOWYHXUSPAIBRCJ    Y        Q
    //         II    AJDKSIRUXBLHWTMCQGZNPYFVOE    M        E
    //        III    BDFHJLCPRTXVZNYEIWGAKMUSQO    D        V
    //         IV    ESOVPZJAYQUIRHXLNFTGKDCMWB    R        J
    //          V    VZBRGITYUPSDNHLXAWMJQOFECK    H        Z
    //         VI    JPGVOUMFYQBENHZRDKASXLICTW    H,U      Z,M
    //        VII    NZJHGRCXMYSWBOUFAIVLPEKQDT    H,U      Z,M
    //       VIII    FKQHTLXOCBJSPDZRAMEWNIUYGV    H,U      Z,M

    //          b    LEYJVCNIXWPBQMDRTAKZGFUHOS    M-4 only
    //          g    FSOKANUERHMBTIYCWLQPZXVGJD    M-4 only

    // Reflector
    //         B     YRUHQSLDPXNGOKMIEBFZCWVJAT
    //         C     FVPJIAOYEDRZXWGCTKUQSBNMHL
    //  Thin B     ENKQAUYWJICOPBLMDXZVFTHRGS    M-4 only
    //  Thin C     RDOBJNTKVEHMLFCWZAXGYIPSUQ    M-4 only

    Plugboard plugboard;
    Rotor rotor1;
    Rotor rotor2;
    Rotor rotor3;
    Reflector reflector;

    public Enigma(Plugboard plugboard, Rotor rotor1, Rotor rotor2, Rotor rotor3,
        Reflector reflector) {
        this.plugboard = plugboard;
        this.rotor1 = rotor1;
        this.rotor2 = rotor2;
        this.rotor3 = rotor3;
        this.reflector = reflector;
    }

    public static Map<Character, Character> mapChars(String key, String value) {
        Map<Character, Character> result = new HashMap<>();
        char[] keyChars = key.toCharArray();
        char[] valueChars = value.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            result.put(keyChars[i], valueChars[i]);
        }
        return result;
    }



    public Character pressChar(Character c) {
        int p;
        boolean shouldTurn2 = rotor1.notchOverPawl()|| rotor2.notchOverPawl();
        boolean shouldTurn3 = rotor2.notchOverPawl();
        if (shouldTurn2){
            rotor2.turn();
        }
        if(shouldTurn3){
            rotor3.turn();
        }
        this.rotor1.turn();
        c = this.plugboard.fw(c);
        p = this.rotor1.fw((int) c - 'A');
        p = this.rotor2.fw(p);
        p = this.rotor3.fw(p);
        p = this.reflector.reflect(p);
        p = this.rotor3.bw(p);
        p = this.rotor2.bw(p);
        p = this.rotor1.bw(p);
        c = (char) ('A' + p);
        c = this.plugboard.bw(c);
        return c;
    }

    public String pressString(String s) {
        char[] msgChars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : msgChars) {
            Character result = pressChar(c);
            System.out.print(result);
            sb.append(result);
        }
        return new String(sb);
    }

    static public void main(String[] arg) {
        String P;
        String M;
        //        String initPos = "HAX";
        //        String msgKey = "HAX";
        //        int[] ringSetting = {19, 7, 16};
        //        Map<Character, Character> plugboard = new HashMap<>();
        //        plugboard.put('Q', 'W');
        //        plugboard.put('A', 'S');
        //        plugboard.put('E', 'R');
        //        plugboard.put('D', 'F');
        //        plugboard.put('T', 'Z');
        //        plugboard.put('G', 'H');
        //        plugboard.put('U', 'I');
        //        plugboard.put('J', 'K');
        //        plugboard.put('O', 'P');
        //        plugboard.put('L', 'Y');
        //        int key1, key2, key3 = 0;
        //        Scanner in = new Scanner(System.in);
        //        System.out.println("input plain text");
        //        P = in.nextLine();
        //        System.out.println("input key" + "\n" + "input 1st roll init:");
        //        key1 = in.nextInt();
        //        System.out.println("input 2nd roll init:");
        //        key2 = in.nextInt();
        //        System.out.println("input 3rd roll init:");
        //        key3 = in.nextInt();
        //        P = P.toUpperCase();
        //        P = P.replaceAll("[^A-Z]", "");
        //        Encrypt en = new Encrypt();
        //        Decrypt dec = new Decrypt();
        //        M = en.encrypt(key1, key2, key3, P);
        //        dec.decrypt(key1, key2, key3, M);

        Rotor rotor3 = new Rotor(Rotor.WHEEL_VIII, "HU", "ZM", "VIII", 'H', 19, null);
        Rotor rotor2 = new Rotor(Rotor.WHEEL_VI, "HU", "ZM", "VI", 'A', 7, rotor3);
        Rotor rotor1 = new Rotor(Rotor.WHEEL_VIII, "HU", "ZM", "VIII", 'X', 16, rotor2);
//        Rotor rotor3 = Rotor.ROTOR_VIII;
//        Rotor rotor2 = Rotor.ROTOR_VI;
//        Rotor rotor1 = Rotor.ROTOR_VIII;
//        rotor3.setRingSetting(16);
//        rotor2.setRingSetting(7);
//        rotor1.setRingSetting(19);
//        rotor3.setDisplayPos('X');
//        rotor2.setDisplayPos('A');
//        rotor1.setDisplayPos('H');
        Plugboard plugboard = new Plugboard("QAEDTGUJOL", "WSRFZHIKPY");
//        QW AS ER DF TZ GH UI JK OP LY
        Reflector reflector = new Reflector(Reflector.REFLECTOR_C);
        Enigma enigma = new Enigma(plugboard, rotor1, rotor2, rotor3, reflector);
        System.out.println(enigma.pressString("HAX"));
    }
}


class Rotor {
    public static final String WHEEL_I = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    public static final String WHEEL_II = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    public static final String WHEEL_III = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    public static final String WHEEL_IV = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
    public static final String WHEEL_V = "VZBRGITYUPSDNHLXAWMJQOFECK";
    public static final String WHEEL_VI = "JPGVOUMFYQBENHZRDKASXLICTW";
    public static final String WHEEL_VII = "NZJHGRCXMYSWBOUFAIVLPEKQDT";
    public static final String WHEEL_VIII = "FKQHTLXOCBJSPDZRAMEWNIUYGV";
    //          I    EKMFLGDQVZNTOWYHXUSPAIBRCJ    Y        Q
    //         II    AJDKSIRUXBLHWTMCQGZNPYFVOE    M        E
    //        III    BDFHJLCPRTXVZNYEIWGAKMUSQO    D        V
    //         IV    ESOVPZJAYQUIRHXLNFTGKDCMWB    R        J
    //          V    VZBRGITYUPSDNHLXAWMJQOFECK    H        Z
    //         VI    JPGVOUMFYQBENHZRDKASXLICTW    H,U      Z,M
    //        VII    NZJHGRCXMYSWBOUFAIVLPEKQDT    H,U      Z,M
    //       VIII    FKQHTLXOCBJSPDZRAMEWNIUYGV    H,U      Z,M
    public static Rotor ROTOR_I = new Rotor(WHEEL_I, "Y", "Q", "I");
    public static Rotor ROTOR_II = new Rotor(WHEEL_II, "M", "E", "II");
    public static Rotor ROTOR_III = new Rotor(WHEEL_III, "D", "V", "III");
    public static Rotor ROTOR_IV = new Rotor(WHEEL_IV, "R", "J", "IV");
    public static Rotor ROTOR_V = new Rotor(WHEEL_V, "H", "Z", "V");
    public static Rotor ROTOR_VI = new Rotor(WHEEL_VI, "HU", "ZM", "VI");
    public static Rotor ROTOR_VII = new Rotor(WHEEL_VII, "HU", "ZM", "VII");
    public static Rotor ROTOR_VIII = new Rotor(WHEEL_VIII, "HU", "ZM", "VIII");

    String string;
    String notch;
    String turnover;
    String name;
    int ringSetting;
    int[] mapFuntions;
    int pos;
    Rotor nextRotor;
    int displayPos;

    public Rotor(String string, String notch, String turnover, String name) {
        this.string = string;
        this.notch = notch;
        this.turnover = turnover;
        this.name = name;
        this.string = string;

        this.notch = notch;
        this.turnover = turnover;

        this.name = name;
        this.setRingSetting(0);

        this.nextRotor = null;
        this.pos = 0;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }



    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public int getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(int ringSetting) {
        this.ringSetting = ringSetting;
        this.mapFuntions = mapFuntions(Enigma.ALPHABET, string);
    }



    public void setPos(int pos) {
        this.pos = pos;
    }

    public Rotor getNextRotor() {
        return nextRotor;
    }

    public void setNextRotor(Rotor nextRotor) {
        this.nextRotor = nextRotor;
    }

    public Rotor(String string, String notch, String turnover, String name, char initPos,
        int ringSetting, Rotor nextRotor) {
        this.string = string;

        this.notch = notch;
        this.turnover = turnover;

        this.name = name;
        this.setRingSetting(ringSetting);
        this.nextRotor = nextRotor;
        this.setDisplayPos(initPos - 'A');
    }

    public int getDisplayPos() {
        return (pos + ringSetting) % 26;
    }

    public void setDisplayPos(int displayPos) {
        this.pos = (displayPos - ringSetting + 26) % 26;
    }

    private int mapRingSettingFw(int inPos) {
        return (inPos + ringSetting) % 26;
    }

    private int mapRingSettingBw(int inPos) {
        return (inPos - ringSetting + 26) % 26;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public static int[] mapFuntions(String key, String value) {
        int[] result = new int[26];
        char[] keyChars = key.toCharArray();
        char[] valueChars = value.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int function = valueChars[i] - keyChars[i];
            if (function < 0) {
                function += 26;
            }
            result[i] = function;
        }
        return result;
    }

    /**
     * e.g. ROTOR_I A should take 2nd funtion(mapFuntions[1]) if position is 1(=B-02)
     *
     * @param inPos
     * @return
     */
    public int getFunctionIndex(int inPos) {
        System.out.println(this.name+" position:" + pos);
        return (inPos + pos) % 26;
    }

    public void turn() {
        //checkTurnover();
        this.pos++;
        this.pos=this.pos%26;
        System.out.println(this.name+" turn " + pos);
    }

    public boolean notchOverPawl(){
        for (char turnoverChar : this.turnover.toCharArray()) {
            if (this.pos == Enigma.ALPHABET.indexOf(turnoverChar)) {
                return true;
            }
        }
        return false;
    }



    public void checkTurnover() {
        if (nextRotor == null) {
            return;
        }
        for (char turnoverChar : this.turnover.toCharArray()) {
            if (this.pos == Enigma.ALPHABET.indexOf(turnoverChar)) {
                nextRotor.turn();
            }
        }
    }

//    public Character fw(Character c) {
//        int index = Enigma.ALPHABET.indexOf(c);
//        char c1 = (char) (index + ringSetting + 'A');
//        Character result = (char) ('A' + (c - 'A' + mapFuntions[getFunctionIndex(c1)]) % 26);
//        //        int offset = this.pos + this.ringSetting;
//        //        int output = (index + offset) % 26;
//        //        return chars[output];
//        return result;
//    }

    /**
     * @param inPos 0-25 for A-Z
     * @return
     */
    public int fw(int inPos) {
        //inPos=mapRingSettingFw(inPos);
        int result = (inPos + mapFuntions[getFunctionIndex(inPos)]) % 26;
        //        int offset = this.pos + this.ringSetting;
        //        int output = (index + offset) % 26;
        //        return chars[output];
        System.out.println("fw "+(char) ('A' + result));
        return result;
    }

//    public Character bw(Character c) {
//        int index = Enigma.ALPHABET.indexOf(c);
//        int offset = this.pos + this.ringSetting;
//        int output = (index - offset + 26) % 26;
//        return Enigma.ALPHABET.charAt(output);
//    }

    /**
     * @param inPos 0-25 for A-Z
     * @return
     */
    public int bw(int inPos) {
        for (int i = 0; i < 26; i++) {
            //int result = (i + mapFuntions[getFunctionIndex(i)]) % 26;
            if (fw(i) == inPos) {
                //      i=mapRingSettingBw(i);
                System.out.println("bw "+ (char) ('A' + i));
                return i;
            }
        }

        throw new RuntimeException("bw invalid input:" + inPos);
    }
}


class Reflector {
    public static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    public static final String REFLECTOR_C = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
    String string;
    Map<Character, Character> map = new HashMap<>();

    public Reflector(String string) {
        this.string = string;
        map = Enigma.mapChars(Enigma.ALPHABET, string);
    }

    //    Character reflect(Character c) {
    //        return null;
    //    }
    int reflect(int inPos) {
        return (int) map.get((char) (inPos + 'A')) - 'A';
    }
}


class Plugboard {
    String keys;
    String values;
    Map<Character, Character> fwMap = new HashMap<>();
    Map<Character, Character> bwMap = new HashMap<>();

    public Plugboard(String keys, String values) {
        this.keys = keys;
        this.values = values;
        fwMap = Enigma.mapChars(keys, values);
        bwMap = Enigma.mapChars(values, keys);
    }

    public Character fw(Character c) {
        Character result = fwMap.get(c);
        if (result == null) {
            result = c;
        }
        return result;
    }

    public Character bw(Character c) {
        Character result = bwMap.get(c);
        if (result == null) {
            result = c;
        }
        return result;
    }
}


