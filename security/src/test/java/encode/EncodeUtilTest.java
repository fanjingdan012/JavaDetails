package encode;

//import file.FileUtil;

import hash.HashUtil;
import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;

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
        assertEquals("msTrKY", EncodeUtil.hex2String("6d7354724b59"));
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
        System.out.println(EncodeUtil.int2Hex(2500));
        System.out.println(EncodeUtil.int2Hex(1666));
        System.out.println(EncodeUtil.int2Hex(2600));
        System.out.println(EncodeUtil.int2Hex(1766));
        System.out.println(EncodeUtil.hex2String("478B93A2CB4E4E8C482C780C9C07C0FD").toLowerCase());
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

    public void getFirstBlockChainNonce() {
//        System.out.println(EncodeUtil.hex2String(EncodeUtil.int2Hex(-72539512)));
        BigInteger b = new BigInteger("709e3e28", 16);
        System.out.println(b);
    }

    @Test
    public void getSum() {
        int sum = 0;
        for (int x = 3; x < 1000; x++) {
            for (int z = 3; z < 1000; z++) {
                sum += (f(x, z));
            }
        }
        System.out.println(sum);
    }


    @Test
    public void read() {
        System.out.println(EncodeUtil.hex2String("00007fffdbf6bfc8"));
        System.out.println(EncodeUtil.hex2String("0000000100040000"));
        System.out.println(EncodeUtil.hex2String("414c465f4654437b"));
        System.out.println(EncodeUtil.hex2String("7d335f4e57505f47"));
        System.out.println(EncodeUtil.hex2String("0000002000000000"));
        System.out.println(EncodeUtil.hex2String("0000000000000001"));
        System.out.println(EncodeUtil.hex2String("0000000000000001"));
        System.out.println(EncodeUtil.hex2String("00007ffcfdfd3da8"));
        System.out.println(EncodeUtil.hex2String("00007ffcfdfd3db8"));
        System.out.println(EncodeUtil.hex2String("0000000000000002"));
        System.out.println(EncodeUtil.hex2String("7025702570257025"));
        System.out.println(EncodeUtil.hex2String("7025702570257025"));
    }

    @Test
    public void getCharFromBinary() {
//        4D79207011011001010110001101110010011001010111010000100000011010010111
        String hex = EncodeUtil.binary2Hex("0100110101111001001000000111001101100101011000110111001001100101011101000010000001101001011100110010000000111010001000000100001101101111010000110110111101001110011101010011011100110001");
        String hex2 = EncodeUtil.binary2Hex("00110010000000111010001000000100001101101111010000110110111101001110011101010011011100110001");
        System.out.println(EncodeUtil.hex2String("4d7920736563726574206973203a2050346e43614b33"));
        System.out.println(EncodeUtil.hex2String(hex));
        System.out.println(EncodeUtil.hex2String(hex2));

    }

    @Test
    public void getF() {
        BigInteger sum = BigInteger.ZERO;
        for (int x = 3; x < 30000; x++) {
            for (int z = 3; z < 30000; z++) {
                Integer f = f1(x, z);
                sum = sum.add(new BigInteger(f.toString()));
            }
            System.out.println("x=" + x);
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
        if (x - z == 2) {
            return x - 3;
        }
        if (x - z == 1) {
            return x - 2;
        }
        return x - 4;
    }

    private int f(int x, int z) {
        if (x > z) {
            return x - 2;
        }
        return f(f(x + 3, z), z);
    }

    @Test
    public void hexo2Bytes() {
        byte[] bytes = EncodeUtil.hex2Bytes("e3b41084cd45f2ad");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + ",");
        }
    }

    @Test
    public void decryptBlocky() {
        byte[] bytes = EncodeUtil.hex2Bytes("f6d67358869f04f893b7150497ad0c3399a8030095a804339ca90e337da10902");
        for (int i = 0; i < bytes.length; i += 4) {

            for (int j = 4; j >= 1; j--) {
                byte[] bytes1 = new byte[4];
                bytes1[0] = round((bytes[i] & 0xFF) - (1337 * 0 + 1));
                bytes1[1] = round((bytes[i + 1] & 0xFF) - (1337 * 1 + 1));
                bytes1[2] = round((bytes[i + 2] & 0xFF) - (1337 * 2 + 1));
                bytes1[3] = round((bytes[i + 3] & 0xFF) - (1337 * 3 + 1));
                bytes1 = turn(bytes1, j);
                byte[] bytes2 = shift(bytes1);
                bytes[i] = bytes2[0];
                bytes[i + 1] = bytes2[1];
                bytes[i + 2] = bytes2[2];
                bytes[i + 3] = bytes2[3];
            }


        }
        System.out.println(EncodeUtil.bytes2Hex(bytes));
    }

    @Test
    public void decryptBlocky1() {
        System.out.println(EncodeUtil.bytes2Hex(turn(EncodeUtil.hex2Bytes("23456701"), 1)));
        System.out.println(EncodeUtil.bytes2Hex(shift(EncodeUtil.hex2Bytes("23016745"))));
    }

    private byte round(int i) {

        i %= 0xFF;
        if (i < 0) {
            i += 0xFF;
        }
        return (byte) i;

    }

    private byte[] turn(byte[] bytes, int round) {
        byte[] result = new byte[4];
        result[0] = bytes[(-round + 8) % 4];
        result[1] = bytes[(1 - round + 8) % 4];
        result[2] = bytes[(2 - round + 8) % 4];
        result[3] = bytes[(3 - round + 8) % 4];
        return result;

    }

    private byte[] shift(byte[] bytes) {
        byte[] result = new byte[4];
        result[0] = bytes[1];
        result[1] = bytes[0];
        result[2] = bytes[3];
        result[3] = bytes[2];
        return result;

    }

    @Test
    public void guessKey() {

        byte[] bytes = EncodeUtil.hex2Bytes("7d62f9d90e0389a21f0e96ba163d9ab00d0a9cab0e3d98ab183d9fac130c80a4");
        byte[] b0 = new byte[bytes.length / 4];
        byte[] b1 = new byte[bytes.length / 4];
        byte[] b2 = new byte[bytes.length / 4];
        byte[] b3 = new byte[bytes.length / 4];
        for (int j = 0; j < bytes.length; j += 4) {
            b0[j / 4] = bytes[j];
            b1[j / 4] = bytes[j + 1];
            b2[j / 4] = bytes[j + 2];
            b3[j / 4] = bytes[j + 3];
        }


        String readable = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        byte[] bs = new byte[b0.length];
//        for (byte i = -128; i < 127; i++) {
        byte i = 127;
        boolean isReadableKey = true;
        for (int j = 0; j < b0.length; j++) {

            bs[j] = (byte) (b0[j] ^ i);

        }
        String s = EncodeUtil.hex2String(EncodeUtil.bytes2Hex(bs));
        for (char c : s.toCharArray()) {


            if (readable.indexOf(c) < 0) {
                isReadableKey = false;
                break;
            }
        }
        if (isReadableKey) {
            System.out.print("," + i);
        }
//        }
    }

    @Test
    public void decryptTry() {
        byte[] bytes = EncodeUtil.hex2Bytes("0e0389a21f0e96ba163d9ab00d0a9cab0e3d98ab183d9fac130c80a4");
        byte[] key = EncodeUtil.hex2Bytes("7d62f9d9");
        byte[] dec = new byte[bytes.length];

        for (int m = 0; m < bytes.length; m++) {
            dec[m] = (byte) (bytes[m] ^ key[m % 4]);
        }
        System.out.println(EncodeUtil.hex2String(EncodeUtil.bytes2Hex(dec)));
    }
}


