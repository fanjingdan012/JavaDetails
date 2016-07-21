package checksum.md5;

/**
 * Created by I312177 on 11/13/2015.
 */


import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.math.BigDecimal;
        import java.nio.MappedByteBuffer;
        import java.nio.channels.FileChannel;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;


public class MD5Test {
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(MD5Test.class.getName()
                    + "??????MessageDigest???MD5Util?");
            nsaex.printStackTrace();
        }
    }

    public static String getMD5(String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            throw new IOException("???????,???...");
        }
        String md5 = getFileMD5String(file);
        return md5 ;
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }
    public static void main(String [] args) throws IOException{
        char s = 3;
        String password = "20140401"+s+"3\n"+
                "3109EEH8DE1697B4B0CA9FFB4CABD7984BA"+s+"0003"+s+"1"+s+"310105196702210000"+s+"???"+s+"20140401\n"+
                "429137FAA73DEA6441EB6B29B91ECF9FG76"+s+"0003"+s+"1"+s+"429005198905140900"+s+"??"+s+"20140401\n"+
                "520137FAA73DEA6441EB622EB91ECF9FG76"+s+"0003"+s+"1"+s+"52062419880122957X"+s+"???"+s+"20140401";
        System.out.println(getMD5("C:\\Users\\I312177\\Desktop\\cron.txt"));
        System.out.println(checkPassword(password, "2a753f48fa353333f42e329d14d725a8")) ;
    }
}
