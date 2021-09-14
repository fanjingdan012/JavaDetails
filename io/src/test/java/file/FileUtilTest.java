package file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FileUtilTest {

    @Test
    public void testReadFileByChars() throws Exception {
        FileUtil.readFileByChars("./pom.xml");
    }

    @Test
    public void testReadFileByLines() throws Exception {
        FileUtil.readFileByLines("fileName");
    }

    @Test
    public void testReadFileByRandomAccess() throws Exception {
        FileUtil.readFileByRandomAccess("fileName");
    }


    @Test
    public void testWriteASet() throws Exception {
        FileUtil.writeASet(0);
    }

    @Test
    public void testReadTxtFileIntoStringArrList() throws Exception {
        List<String> result = FileUtil.readTxtFileIntoStringArrList("filePath");
        Assert.assertEquals(Arrays.<String>asList("String"), result);
    }

    @Test
    public void testReadFileByBytes() throws Exception {
        byte[] fileBytes = FileUtil.readFileByBytes("./3miFMQZ.bmp");
        byte[] bytes = new byte[fileBytes.length];
        for (int i = 0; i < fileBytes.length; i++) {
            byte tempbyte = fileBytes[i];

            if (i == 54) {
                System.out.println();
            }
            if (i > 54 && i % 3 == 0) {
                System.out.println();
            }
            System.out.print(tempbyte + ",");
//            if (i > 54) {
//                if (tempbyte == 10) {
//                    bytes[i - 1] = (byte) 0;
//                }
//                if (tempbyte == 7) {
//                    bytes[i - 1] = (byte) 0;
//                }
//                if (tempbyte == 9) {
//                    bytes[i - 1] = (byte) 0;
//                }
//                if (tempbyte == 0) {
//                    bytes[i - 1] = (byte) 255;
//                }
//                //System.out.print(tempbyte+",");
//            }
        }

    }

    @Test
    public void test() {
        List<String> result = FileUtil.readTxtFileIntoStringArrList("hacked_dates.txt");
        HashMap<String, Integer> histo = new HashMap<>();

        for (String dateStr : result) {
            String[] ss = dateStr.split("/");

            String date = new Date(Integer.parseInt(ss[2]) - 1900, Integer.parseInt(ss[1]) - 1, Integer.parseInt(ss[0])).toString();
            System.out.println(date);
            String weekday = date.substring(0, 3);
            Integer times = histo.get(weekday);
            if (times == null) {
                times = 0;

            }
            times++;
            histo.put(weekday, times);
        }
        System.out.println(histo);
    }

    @Test
    public void readFileByBytes(String fileName) {
        File file = new File(fileName);
        File file2 = new File("C:\\fjd\\bb.bmp");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        byte[] bbytes = new byte[(int) file.length()];
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            int i = 0;
            while ((tempbyte = in.read()) != -1) {


                System.out.print(tempbyte + " ");
                bbytes[i] = (byte) tempbyte;
                i++;
                if (i == 54) {
                    System.out.println();
                }
                if (i > 54 && i % 3 == 0) {
                    System.out.println();
                }
                if (i > 54) {
                    if (tempbyte == 10)
                        bbytes[i - 1] = (byte) 0;
                    if (tempbyte == 7)
                        bbytes[i - 1] = (byte) 0;
                    if (tempbyte == 9)
                        bbytes[i - 1] = (byte) 0;
                    if (tempbyte == 0)
                        bbytes[i - 1] = (byte) 255;
                }

            }
            fos = new FileOutputStream(file2);
            fos.write(bbytes);
            fos.close();

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }


    @Test
    public void writeFileByBytes() {
        File file2 = new File("C:\\fjd\\bb.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        byte[] bbytes = new byte[4];
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            bbytes[0] = (byte) 251;
            bbytes[1] = (byte) 251;
            bbytes[2] = (byte) 251;
            bbytes[3] = (byte) 251;
            fos = new FileOutputStream(file2);
            fos.write(bbytes);
            fos.close();
        } catch (Exception e) {

        }

    }


    @Test
    public void readAABmp(String[] args) {
        String fileName = "aa.bmp";
        readFileByBytes(fileName);
//        ReadFromFile.readFileByChars(fileName);
//        ReadFromFile.readFileByLines(fileName);
//        ReadFromFile.readFileByRandomAccess(fileName);
//        writeFileByBytes();
    }


}