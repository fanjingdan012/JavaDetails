package crypt;// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class CTFChallenge {
    private static final BigInteger one = new BigInteger("1");
    private BigInteger modulus;
    private BigInteger publicKey;
    private BigInteger privateKey;

    public CTFChallenge() {
        BigInteger p = new BigInteger("13767456700928940679");
        BigInteger q = new BigInteger("12386380973634958649");
        BigInteger phi = p.subtract(one).multiply(q.subtract(one));
        this.modulus = p.multiply(q);
        this.publicKey = new BigInteger("65537");
        this.privateKey = this.publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(this.publicKey, this.modulus);
    }

    public String encryptWithRSA(String message) {
        String messageAfterPadding = this.paddingFirstPartOfMessage(message);
        return this.encrypt(new BigInteger(messageAfterPadding)).toString();
    }

    private String paddingFirstPartOfMessage(String firstPartOfMessage) {
        char[] messgaeAsCharArray = firstPartOfMessage.toCharArray();
        StringBuffer buf = new StringBuffer();
        char[] var4 = messgaeAsCharArray;
        int var5 = messgaeAsCharArray.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            char messageChar = var4[var6];

            int lengthOfNumericValue = ("" + (int)messageChar).length();
            if (lengthOfNumericValue == 1) {
                buf.append("88" + (int)messageChar);
            }

            if (lengthOfNumericValue == 2) {
                buf.append("8" + (int)messageChar);
            }

            buf.append((int)messageChar);
        }

        return buf.toString();
    }

    public String md5(String source) {
        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for(int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 255 | 256).toUpperCase().substring(1, 3));
            }
        } catch (Exception var6) {
            return null;
        }

        return sb.toString();
    }

    public static void main1(String[] args) {
        if (args.length != 1) {
            System.out.println("Answer is wrong");
        } else if (args[0].length() != 15) {
            System.out.println("Answer is wrong");
        } else {
            String firstPartOfMessage = args[0].substring(0, 10);
            String lastPartOfMessage = args[0].substring(10);
            CTFChallenge challenge = new CTFChallenge();
            String encryptedMessage = challenge.encryptWithRSA(firstPartOfMessage);
            boolean passFirstPart = false;
            if ("148728508633806583981745142113768023817".equals(encryptedMessage)) {
                passFirstPart = true;
            }

            boolean passSecondPart = challenge.md5(lastPartOfMessage).equals("E0F2D33D0AD8B260532AAD36484E8192");
            if (passFirstPart && passSecondPart) {
                System.out.println("Great! Congratulations to catch the flag! Go to CTF platform to validate your findings!");
            } else {
                System.out.println("Answer is wrong");
            }

        }
    }

    private BigInteger decrypt(BigInteger encryptedMsg){
        return encryptedMsg.modPow(this.privateKey, this.modulus);
    }
    private static void bruteForceMd5(){
        CTFChallenge challenge = new CTFChallenge();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        for(char i:chars) {
            for (char m:chars) {
                for (char j:chars) {
                    for (char k:chars) {
                        for (char l:chars) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(i);
                            sb.append(m);
                            sb.append(j);
                            sb.append(k);
                            sb.append(l);
                            String s = new String(sb);
                            System.out.println(s);
                           // System.out.println(challenge.md5(s));
                            boolean passSecondPart = challenge.md5(s).equals("E0F2D33D0AD8B260532AAD36484E8192");

                            if (passSecondPart) {
                                System.out.println("return "+s);
                                return;
                            }
                        }
                    }
                }
            }
        }
        return;
    }
    public static void main2(String[] args) {
        CTFChallenge challenge = new CTFChallenge();
//        this.privateKey = this.publicKey.modInverse(phi);
        BigInteger p = new BigInteger("23");
        System.out.println( "p=23" );
        BigInteger q = new BigInteger("19");
        System.out.println( "q=19" );
        BigInteger n = p.multiply(q);
        System.out.println( "n=p*q=437" );
        BigInteger phi = new BigInteger(22*18+"");
        System.out.println( "phi=(p-1)*(q-1)=396" );
        BigInteger e = new BigInteger("17");
        System.out.println( "e=17" );
        BigInteger d = e.modInverse(phi);
        // print bi3 value
        System.out.println( "d=e.modeInverse(phi)=233, i.e. "+"d("+d+")*e("+e+") mod phi("+phi+") = 1" );
        System.out.println( "public key is (n,e)" );
        System.out.println( "private key is (n,d)" );
        BigInteger plainMsg=new BigInteger("100");
        BigInteger encryptedMsg=plainMsg.modPow(e,n);
        System.out.println( "encryptedMsg=plainMsg.modePow(e,n)=plainMsg^e mod n=(100^17)%437=289（need BigInteger)" );
        System.out.println( "encrypt 100:"+encryptedMsg );
        BigInteger plainMsg2=encryptedMsg.modPow(d,n);
        System.out.println( "plainMsg2=encryptedMsg.modePow(d,n)=encryptedMsg^d mod n=(289^233)%437=100" );
        System.out.println( "decrypt "+encryptedMsg+":"+plainMsg2 );



//        for (int m = 65; m < 123; m++) {
//            System.out.print((char)m);
//        }


//        BigInteger encryptedMsg = new BigInteger("148728508633806583981745142113768023817");
//        BigInteger originalMsg = challenge.decrypt(encryptedMsg)
//        System.out.println(t.toString());
//        System.out.println(challenge.encrypt(new BigInteger("87777897971001018737311086767104105110")));
        //87777897971001018737311086767104105110
//        System.out.print((char)77);
//        System.out.print((char)97);
//        System.out.print((char)100);
//        System.out.print((char)101);
//        System.out.print((char)73);
//        System.out.print((char)110);
//        System.out.print((char)67);
//        System.out.print((char)104);
//        System.out.print((char)105);
//        System.out.print((char)110);


//        String a = challenge.paddingFirstPartOfMessage("MadeInChin");
//        System.out.println(a);
//        String lastPartOfMessage = "123456789876543".substring(10);
//        System.out.println(lastPartOfMessage);

      // bruteForceMd5();



    }
//https://crypto-tasks.livejournal.com/28226.html
    public static void main(String[] args) {
        String key="DACyLQ8DppU";
        String em="GZVURTKQBKUNKTIKEVQZNZOBKYUNMIJMTD MOKEMZUTAFZNOEFHMWVJFQBWQZNNYEEM KTWJVBMDPLOWPLRBKYLNNEVLZTFQEFQKTK KPEWWHDJTPNDCIAJK\n" +
                "YKMBODDRUMCZNMZTPFDJQUAAIKHZUPMW WYUAAUDTPDAAZENKTMKEBLZNLTILEVETPHZ TRYETZNMBKCAZVMDKZETFDJENODEKVDDPA JOR";
        char[] keyc=key.toCharArray();
        char[] emc = em.toCharArray();
        System.out.println((int)'A');
        System.out.println((int)'Z');
        int i = 0;
        Map<Character,Character> m = new HashMap();
        m.put('A','Z');
        m.put('B','I');
        m.put('C','O');
        m.put('D','P');
        m.put('E','K');
        m.put('F','C');
        m.put('G','V');
        m.put('H','B');
        m.put('I','U');
        m.put('J','X');
        m.put('K','R');
        m.put('L','A');
        m.put('M','Y');
        m.put('N','T');
        m.put('O','D');
        m.put('P','H');
        //m.put('Q','N');
        m.put('R','E');
        m.put('S','N');
        m.put('T','M');
        m.put('U','J');
        m.put('V','S');
        m.put('W','Q');
        m.put('X','G');
        m.put('Y','F');
        m.put('Z','W');
        Map<Character,Character> m2 = new HashMap<Character, Character>();
        for(Map.Entry<Character,Character> entry:m.entrySet()){
            m2.put(entry.getValue(),entry.getKey());
        }
        for(char c:emc){

            System.out.print(m2.get(c));
            i++;
        }



    }
}
