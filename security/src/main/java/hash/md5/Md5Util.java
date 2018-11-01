package hash.md5;



import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.MappedByteBuffer;
        import java.nio.channels.FileChannel;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Md5Util {
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName()
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
    public static void main1(String [] args) throws IOException{
        char s = 3;
        String password = "20140401"+s+"3\n"+
                "3109EEH8DE1697B4B0CA9FFB4CABD7984BA"+s+"0003"+s+"1"+s+"310105196702210000"+s+"???"+s+"20140401\n"+
                "429137FAA73DEA6441EB6B29B91ECF9FG76"+s+"0003"+s+"1"+s+"429005198905140900"+s+"??"+s+"20140401\n"+
                "520137FAA73DEA6441EB622EB91ECF9FG76"+s+"0003"+s+"1"+s+"52062419880122957X"+s+"???"+s+"20140401";
        System.out.println(getMD5("C:\\Users\\I312177\\Desktop\\cron.txt"));
        System.out.println(checkPassword(password, "2a753f48fa353333f42e329d14d725a8")) ;
    }
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }

    public String getSalt() {
        String salt=new String();

        salt = new String()+(1000+(int)(Math.random()*8000-1));

        return salt;

    }


    public String toMD5(String password) {
        String myHash=new String();
        String salt = getSalt();

        password = password+salt;

        System.out.println("Password:"+password);
        System.out.println("Salt:"+salt);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                .printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myHash;

    }



    private static void aa(){
        char c='a';
        for(int i=0;i<26;i++){
            System.out.println(c);
            c++;
        }
    }

    private static String getmd5(String password){
        MessageDigest md;
        String myHash="";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                .printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myHash;
    }

    private static boolean tryPwd(){
        for(int i = 1000;i<9001;i++){
            //			String nstr= ""+pwd+i;
            String md5=getmd5("iamnoteasytobreak"+i);
            if("AAF8A6D56D4A0AB1E2D1D49329AB6956".equals(md5)){
                System.out.println(i);
                return true;
            }
        }
        return false;
    }

    public static void main(String [] args) throws IOException{

        // TODO Auto-generated method stub
        //		System.out.println(new MD5Util().toMD5("iamnoteasytobreak"));
        //" AAF8A6D56D4A0AB1E2D1D49329AB6956"
        //		aa();
        //tryPwd();



        List<String> ss = readTxtFileIntoStringArrList("C:\\fjd\\ctf\\amazing.txt");
        System.out.println(ss.get(0));
        System.out.println(getmd5(ss.get(0)));


    }


}
