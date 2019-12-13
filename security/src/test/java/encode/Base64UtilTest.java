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
        String result = Base64Util.decode("AAAAB3NzaC1yc2EAAAADAQABAAABAQDrOXpSRXELqc7cq3Vy753lajR+LK/VGX2EPeiUlaIUsBFPg/zJDHZQPhr9d/xMu/kR8Vn9dXoxpXImE4el2T0Aa169/Mbyqgudf3+zbzm3aJ5xSckvwbvjegiAbPPQFv47SO+FXgVhgaMhTp1Wugj6fV4W3HanlugNaYEO3f01jjawDDf1R4u0PhkhTFi7SdnR4LcC9OgF2yygUxnR5TSQ3i/M8irv3NMAzl4wfM3xIsJqvUd0tA+J21p9x7nZeXSkYIiiuGbEj6OAw6urHYGsSs8MRp+sztu2XyYaPaknWJaS9sJl0k8y2ofSI23FdvHBzxvxfC/YjWiKKhWSS4bL");
System.out.println(result);
//        Assert.assertEquals("plainText", result);
    }
    @Test
    public void testBase32() throws Exception {
        String s1 = "INZHS4DUN5NDCZJUGQ======";
        System.out.println(new String(Base32.decode(s1)));
        System.out.println(EncodeUtil.bytes2Hex(Base32.decode(s1)));
    }




}
