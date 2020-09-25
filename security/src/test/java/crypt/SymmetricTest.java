package crypt;

import org.junit.Assert;
import org.junit.Test;

public class SymmetricTest {

    @Test
    public void testEncryptASE() throws Exception {
        System.out.println(Symmetric.encryptAES("aaa","aaa"));

    }
    @Test
    public void testJWTHS256() throws InterruptedException {
        String key = "1c8bfe8f801d79745c4631d09fff36c82aa37fc4cce4fc946683d7b336b63032";
        String token = Symmetric.buildJWT("account123",key,1000);
        //decrypt
        String account = Symmetric.vaildToken(token,key);
        System.out.println("token validated，token account:"+account);

        //test expire
        try {
            Thread.sleep(2 * 1000);
            account = Symmetric.vaildToken(token, key);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        Assert.fail("expire check didn't work");
    }

}
