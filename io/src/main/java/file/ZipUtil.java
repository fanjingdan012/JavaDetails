package file;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.List;

public class ZipUtil {
    public static void unzip(String zipFile, String dest, String passwd) throws IOException, ZipException {
        ZipFile zFile = new ZipFile(zipFile);  // 首先创建ZipFile指向磁盘上的.zip文件
//        zFile.setFileNameCharset("GBK");       // 设置文件名编码，在GBK系统中需要设置
//        if (!zFile.isValidZipFile()) {   // 验证.zip文件是否合法，包括文件是否存在、是否为zip文件、是否被损坏等
//            throw new ZipException("压缩文件不合法,可能被损坏.");
//        }
        File destDir = new File(dest);     // 解压目录
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());  // 设置密码
        }
        zFile.extractAll(dest);      // 将文件抽出到解压目录(解压)
    }

    public static void main1(String[] args) throws IOException, ZipException {
        //00075235
        for(int i = 75235;i<11111111;i++){
            String password = ""+i;
            if(password.length()==7){
                password="0"+password;
            }
            if(password.length()==6){
                password="00"+password;
            }
            if(password.length()==5){
                password="000"+password;
            }
            if(password.length()==4){
                password="0000"+password;
            }
            if(password.length()==3){
                password="00000"+password;
            }
            if(password.length()==2){
                password="000000"+password;
            }
            if(password.length()==1){
                password="0000000"+password;
            }
            try {
                ZipUtil.unzip("D:\\githubP\\JavaDetails\\io\\1.zip", "zip", password);
                System.out.println(password);
                break;
            }catch (Exception e){
                System.out.println(password+"wrong");
            }
        }
    }

    public static void main(String[] args) {
        List<String> passwords =FileUtil.readTxtFileIntoStringArrList("D:\\githubP\\JavaDetails\\io\\pwds.txt");
        for(String password:passwords){
            try {
                ZipUtil.unzip("D:\\githubP\\JavaDetails\\io\\1.zip", "zip", password);
                System.out.println(password);
                break;
            }catch (Exception e){
                System.out.println(password+"wrong");
            }
        }
    }
}
