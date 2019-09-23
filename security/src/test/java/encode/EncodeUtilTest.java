package encode;

import org.junit.Test;

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
    public void solveBufferOverflow(){
        //"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1234567890qwertyuiopasdfghjklzxcvbn9^E"
        System.out.println(EncodeUtil.int2Hex(1337));
        System.out.println(EncodeUtil.hex2String("a66b6ea8"));
        System.out.println(EncodeUtil.hex2String(EncodeUtil.int2Hex(1337)));
    }



}