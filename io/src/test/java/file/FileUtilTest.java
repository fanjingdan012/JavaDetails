package file;

import org.junit.Assert;
import org.junit.Test;

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
    public void testWriteFileByBytes() throws Exception {
        FileUtil.writeFileByBytes();
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
        for(int i =0;i<fileBytes.length;i++) {
            byte tempbyte = fileBytes[i];

            if (i == 54) {
                System.out.println();
            }
            if (i > 54 && i % 3 == 0) {
                System.out.println();
            }
            System.out.print(tempbyte+",");
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
    public void test(){
        List<String> result = FileUtil.readTxtFileIntoStringArrList("hacked_dates.txt");
        HashMap<String,Integer> histo = new HashMap<>();

        for(String dateStr:result){
            String[] ss = dateStr.split("/");

            String date = new Date(Integer.parseInt(ss[2])-1900,Integer.parseInt(ss[1])-1,Integer.parseInt(ss[0])).toString();
            System.out.println(date);
            String weekday = date.substring(0,3);
            Integer times = histo.get(weekday);
            if(times==null){
                times =0;

            }
            times++;
            histo.put(weekday,times);
        }
        System.out.println(histo);
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme