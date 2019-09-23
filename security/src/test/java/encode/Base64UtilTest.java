package encode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

public class Base64UtilTest {

    @Test
    public void testEncode() throws Exception {
        String result = Base64Util.encode("plainText");
        Assert.assertEquals("cGxhaW5UZXh0", result);
    }

    @Test
    public void testDecode() throws Exception {
        String result = Base64Util.decode("cGxhaW5UZXh0");
        Assert.assertEquals("plainText", result);
    }
    @Test
    public void testBase32() throws Exception {
        String s1 = "INZHS4DUN5NDCZJUGQ======";
        System.out.println(new String(Base32.decode(s1)));
        System.out.println(EncodeUtil.bytes2Hex(Base32.decode(s1)));
    }




}
