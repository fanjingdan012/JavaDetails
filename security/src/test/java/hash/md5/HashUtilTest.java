package hash.md5;

import crypt.CTFChallenge;
import encode.Base64Util;
import encode.EncodeUtil;
import file.FileUtil;
import hash.HashUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

//md5 length is 32
public class HashUtilTest {

    @Test
    public void testmd5File() throws Exception {
        System.out.println(HashUtil.md5File(new File("./cron.txt")));
    }
@Test
    public void main1() {
        char s = 3;
        String password = "20140401" + s + "3\n" +
                "3109EEH8DE1697B4B0CA9FFB4CABD7984BA" + s + "0003" + s + "1" + s + "310105196702210000" + s + "???" + s + "20140401\n" +
                "429137FAA73DEA6441EB6B29B91ECF9FG76" + s + "0003" + s + "1" + s + "429005198905140900" + s + "??" + s + "20140401\n" +
                "520137FAA73DEA6441EB622EB91ECF9FG76" + s + "0003" + s + "1" + s + "52062419880122957X" + s + "???" + s + "20140401";

//        System.out.println(checkPassword(password, "2a753f48fa353333f42e329d14d725a8")) ;
    }


    @Test
    public void testMd5() throws Exception {
        String result = HashUtil.md5("123456");
        assertEquals("e10adc3949ba59abbe56e057f20f883e", result);
    }


    @Test
    public void bruteForceSaltedMd5() throws Exception {
        for (int i = 1000; i < 9001; i++) {
            String md5 = HashUtil.md5("iamnoteasytobreak" + i);
            if ("AAF8A6D56D4A0AB1E2D1D49329AB6956".equals(md5.toUpperCase())) {
                System.out.println(i);
                break;
            }
        }

    }
    @Test
    public void bruteForceSaltedMd51() throws Exception {
        for (int i = 1000; i < 9001; i++) {
            String md5 = HashUtil.md5("SALTY" + i);
            if ("AAF8A6D56D4A0AB1E2D1D49329AB6956".equals(md5.toUpperCase())) {
                System.out.println(i);
                break;
            }
        }

    }


    //    public static void main(String [] args) throws IOException{
//
//
//        List<String> ss = readTxtFileIntoStringArrList("C:\\fjd\\ctf\\amazing.txt");
//        System.out.println(ss.get(0));
//        System.out.println(getmd5(ss.get(0)));
//
//
//    }
    @Test
    public void solveCTFChallenge() {
        CTFChallenge ctfChallenge = new CTFChallenge();
        BigInteger encryptedMsg = new BigInteger("148728508633806583981745142113768023817");
        BigInteger originalMsg = ctfChallenge.decrypt(encryptedMsg);
        //87777897971001018737311086767104105110
        System.out.print((char) 77);
        System.out.print((char) 97);
        System.out.print((char) 100);
        System.out.print((char) 101);
        System.out.print((char) 73);
        System.out.print((char) 110);
        System.out.print((char) 67);
        System.out.print((char) 104);
        System.out.print((char) 105);
        System.out.print((char) 110);
        //MadeInChin
        //guess start with a in next part
        HashUtil.known5CharsBruteForceMd5("E0F2D33D0AD8B260532AAD36484E8192");
        //a2025
    }

    @Test
    public void testSha256() throws Exception {
        String result = HashUtil.sha256("123456");
        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", result);
    }

    @Test
    public void testSha1() throws Exception {
        String result = HashUtil.sha1("123456");
        assertEquals("7c4a8d09ca3762af61e59520943dc26494f8941b", result);
    }

    @Test
    public void guessHashAlgorithm() throws IOException {
        List<String> lines = FileUtil.readTxtFileIntoStringArrList("base64.txt");
        for (String line : lines) {
            String decoded = Base64Util.decode(line);
            System.out.println(EncodeUtil.hex2String(decoded));
        }

        System.out.println(Base64Util.encode(EncodeUtil.string2Hex("0x0" + HashUtil.md5("admin") + HashUtil.sha1("admin"))));
        System.out.println(HashUtil.sha1("root"));
//        assertEquals(lines.get(0),Base64Util.encode(EncodeUtil.string2Hex("01" + HashUtil.md5("123456") + HashUtil.sha1("123456"))));
//        System.out.println("79"+HashUtil.md5("admin")+ HashUtil.sha1("admin"));
//        System.out.println(EncodeUtil.hex2Int("78"));
    }




    @Test
    public void guessSaltedSha256() throws Exception {
        List<String> passwords = FileUtil.readTxtFileIntoStringArrList("pwds.txt");
        for(String password:passwords){
            for(String salt:passwords) {
                String result = HashUtil.sha256(salt + password);
//            System.out.println(result);
                if ("e3b87e983d54d28f66c94af734967b4b58e7123ce4ed746295a404ee085c43b8".equals(result)) {
                    System.out.println(salt+password);

                }
            }
        }
    }


}