package hash;


import encode.EncodeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtil {

    protected static MessageDigest messagedigest = null;



    public static String md5File(File file) throws IOException {

        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return EncodeUtil.bytes2Hex(messagedigest.digest());
    }

    public static String md5(String s) {
        return md5(s.getBytes());
    }

    private static String md5(byte[] bytes) {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }
        messagedigest.update(bytes);
        return EncodeUtil.bytes2Hex(messagedigest.digest());
    }


    public static void known5CharsBruteForceMd5(String md5) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

        for (char m : chars) {
            for (char j : chars) {
                for (char k : chars) {
                    for (char l : chars) {
                        StringBuilder sb = new StringBuilder();
                        sb.append('a');
                        sb.append(m);
                        sb.append(j);
                        sb.append(k);
                        sb.append(l);
                        String s = new String(sb);
//                            System.out.println(s);
                        // System.out.println(challenge.md5(s));

                        if (HashUtil.md5(s).toUpperCase().equals(md5)) {
                            System.out.println("return " + s);
                            return;
                        }
                    }
                }
            }
        }

        return;
    }


    public static String sha256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = EncodeUtil.bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    public static String sha1(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = EncodeUtil.bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

}
