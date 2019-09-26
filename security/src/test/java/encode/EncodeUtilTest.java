package encode;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class EncodeUtilTest {

    @Test
    public void testBufferToHex() throws Exception {
        String result = EncodeUtil.bytes2Hex(new byte[]{(byte) 0, (byte) 128});
        assertEquals("0080", result);
    }

    @Test
    public void testStringToHex() {
        assertEquals("6765", EncodeUtil.string2Hex("ge"));
    }

    @Test
    public void testHexToString() {
        assertEquals("ge", EncodeUtil.hex2String("6765"));
    }

    @Test
    public void solveXor() {
        String username = "admin123";
        String salt = "11223344";
        String password = "password";
        String hex = "093a0c3e3e706373";
        String s1 = EncodeUtil.hex2String(hex);

        System.out.println(s1);
        String s = s1, key = salt;
        byte[] bytes1 = s.getBytes();
        byte[] bytes2 = key.getBytes();
        byte[] bytes3 = username.getBytes();
        byte[] bytes = new byte[8];

        int byteint;
        for (int i = 0; i < 8; i++) {
            byteint = (int) bytes1[i] ^ (int) bytes2[i];
            bytes[i] = (byte) byteint;
        }
        for (int i = 0; i < 8; i++) {
            byteint = (int) bytes[i] ^ (int) bytes3[i];
            bytes[i] = (byte) byteint;
            System.out.println(bytes[i]);
        }
        String result = new String(bytes);
        System.out.println(result);
    }

    @Test
    public void solveBufferOverflow() {
        //"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1234567890qwertyuiopasdfghjklzxcvbn9^E"
        System.out.println(EncodeUtil.int2Hex(1337));
        System.out.println(EncodeUtil.hex2String("a66b6ea8"));
        System.out.println(EncodeUtil.hex2String(EncodeUtil.int2Hex(1337)));
    }


    @Test
    public void getJsResult() {
        System.out.print((char) 72);
        System.out.print((char) 97);
        System.out.print((char) 99);
        System.out.print((char) 107);
        System.out.print((char) 69);
        System.out.print((char) 114);
        System.out.print((char) 82);
        System.out.print((char) 75);
        System.out.print((char) 69);
        System.out.print((char) 89);
        // "pqiwjvoijvoisdjvoijsduioh"
    }


    @Test
    public void get() {
//        System.out.println(EncodeUtil.hex2String(EncodeUtil.int2Hex(-72539512)));
        BigInteger b = new BigInteger("1209143407476550975641959824312993703149920344437422193042293131572745298662696284279928622412441255652391493241414170537319784298367821654726781089600780498369402167443363862621886943970468819656731959468058528787895569936536904387979815183897568006750131879851263753496120098205966442010445601534305483783759226510120860633770814540166419495817666312474484061885435295870436055727722073738662516644186716532891328742452198364825809508602208516407566578212780807");
        System.out.println(EncodeUtil.hex2String(b.toString(16)));
    }

    @Test
    public void read(){
//        -1619509231,-1621743088,-72539512,-1619509230,0,-2124127640
//%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d
//        1378328608,1376094736,-72539512,1378328609,0,-1628720152,194,1734437990,-1644138907,9,1372807789
//        -2017230783,-2019464688,-72539512,-2017230782,623666213,2144602136,194,1734437990,2130734693,9,-2022751635,118,0,0,15775231,623666213,744760620,1680157796,623666213,744760620,1680157796,623666213
                int i=1734437990;
                i=-1644138907;
                i=9;
                i=744760620;
        System.out.println(EncodeUtil.hex2String(EncodeUtil.int2Hex(i)));
        System.out.println(EncodeUtil.int2Hex(i));
        System.out.println(EncodeUtil.string2Hex("flagIsSeen"));
//        System.out.println(EncodeUtil.hex2Int(EncodeUtil.string2Hex("flagIsSeen")));
//%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d
    }

    @Test
    public void getF() {
        BigInteger sum = BigInteger.ZERO;
        for(int x = 3;x<30000;x++){
            for(int z = 3;z<30000;z++){
                Integer f = f1(x,z);
                sum=sum.add(new BigInteger(f.toString()));
            }
            System.out.println("x="+x);
        }
        System.out.println(sum);
//        for (int x = 3; x < 1000; x++) {
//            for (int z = 3; z < 1000; z++) {
//                System.out.println(x+" "+z);
//                assertEquals(f(x,z), f1(x,z));
//            }
//        }

    }

    private int f1(int x, int z) {
        if (x > z) {
            return x - 2;
        }
        while (x <= z) {
            x += 3;
        }
        if(x-z==2){
            return x-3;
        }
        if(x-z==1){
            return x-2;
        }
        return x - 4;
    }

    private int f(int x, int z) {
        if (x > z) {
            return x - 2;
        }
        return f(f(x + 3, z), z);
    }


}