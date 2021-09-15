package hash;

import encode.EncodeUtil;
import net.HttpUtil;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;

public class CustomizedHashByPictureTest {
    CustomizedHashByPicture customizedHashByPicture = new CustomizedHashByPicture();

    @Test
    public void testCustomizedHash() throws Exception {
        Assert.assertEquals("016556b15b2c630f",EncodeUtil.bytes2Hex(customizedHashByPicture.customizedHash("Hello World")));
        Assert.assertEquals("9759d8cc368a50fe",EncodeUtil.bytes2Hex(customizedHashByPicture.customizedHash("sap{nottheflag}")));
        Assert.assertEquals("438d0af8de1a1a22",EncodeUtil.bytes2Hex(customizedHashByPicture.customizedHash("sap{can i has flag}")));
    }

    @Test
    public void testCustomizedHashCrack() throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        String pwdsStr = httpUtil.sendRequest("https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/Common-Credentials/10-million-password-list-top-1000000.txt","Get",new HashMap<>(),new HashMap<>(),HttpUtil.PARAMETER_TYPE_URLENCODED);
        String[] pwds = pwdsStr.split("\n");
        for(String pwd:pwds){
            String hash=EncodeUtil.bytes2Hex(customizedHashByPicture.customizedHash(pwd));
            System.out.println(hash);
            if(hash.equals("4d65387e51ec072a")){
                System.out.println(pwd);
                break;
            }
        }
    }
}
