package crypt;

import org.junit.Test;

public class AESTest {

    @Test
    public void testEncrypt() throws Exception {
        System.out.println(Symmetric.encryptAES("aaa","aaa"));

    }

}
