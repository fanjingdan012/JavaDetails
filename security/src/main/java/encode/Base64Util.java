package encode;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    public static String encode(String plainText) {
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte;
        try {
            textByte = plainText.getBytes("UTF-8");
            String encodedText = encoder.encodeToString(textByte);
            return encodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static String decode(String encodedText){
        final Base64.Decoder decoder = Base64.getDecoder();
        try {

            return new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }



}
