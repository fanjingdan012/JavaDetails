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
    public void testDecode1() throws Exception {
        String s1 = "INZHS4DUN5NDCZJUGQ======";
        final Base64.Decoder decoder = Base64.getDecoder();
//        System.out.println(EncodeUtil.bytes2Hex(decoder.decode("INZHS4DUN5NDCZJUGQ==")).toUpperCase());
        System.out.println(EncodeUtil.bytes2Hex(decoder.decode("20D6474B80D437934309925419==")).toUpperCase());
        System.out.println(EncodeUtil.bytes2Hex(decoder.decode("DB40FAE3BE01F340F8DFBF77E37D3DF76E78D7==")).toUpperCase());
System.out.println(EncodeUtil.hex2String("0C1E34140137044D35177E3417C0C5045EFB137EC3DC317BE84EFC0F"));
        //        for(int i = 0;i<65535;i++){
//
//
//            //System.out.println("gets"+gets(i,s1));
//
//            String result = Base64Util.decode(gets(i,s1));
//
//            System.out.println(result);
//        }
//        Assert.assertEquals("plainText", result);
    }
    private String gets(int i,String s1){
        String s =Integer.toBinaryString(i);
        char[] chars = s.toCharArray();
        char[] chars1 = s1.toCharArray();
        int k = 0;
        int [] m = new int []{17,16,15,14,13,12,11,10,8,7,6,4,3,2,1,0};
        for(int j = chars.length-1;j>=0;j--) {
            //System.out.println("gets"+gets(i,s1));
            if(chars[j] == '1'){
                chars1[m[k]]=(char)((int)chars1[m[k]]+(97-65));
            }
            k++;
        }
        return new String(chars1);

    }



}
