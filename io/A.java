package com.sap.grc.dpg.web.odata.controller;

import java.io.*;

public class A {


    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
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
        byte[] bbytes = new byte[(int)file.length()];
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            int i = 0;
            while ((tempbyte = in.read()) != -1) {


                System.out.print(tempbyte+" ");
                bbytes[i]=(byte)tempbyte;
                i++;
                if(i==54){
                    System.out.println();
                }
                if(i>54&&i%3==0){
                    System.out.println();
                }
                if(i>54){
                    if(tempbyte==10)
                        bbytes[i-1] =(byte)0;
                    if(tempbyte==7)
                        bbytes[i-1] =(byte)0;
                    if(tempbyte==9)
                        bbytes[i-1] =(byte)0;
                    if(tempbyte==0)
                        bbytes[i-1] = (byte)255;
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
//        try {
//            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字节
//            byte[] tempbytes = new byte[100];
//            int byteread = 0;
//            in = new FileInputStream(fileName);
//            showAvailableBytes(in);
//            // 读入多个字节到字节数组中，byteread为一次读入的字节数
//            while ((byteread = in.read(tempbytes)) != -1) {
//                System.out.write(tempbytes, 0, byteread);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
//    public static void readFileByChars(String fileName) {
//        File file = new File(fileName);
//        Reader reader = null;
//        try {
//            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
//            // 一次读一个字符
//            reader = new InputStreamReader(new FileInputStream(file));
//            int tempchar;
//            while ((tempchar = reader.read()) != -1) {
//                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
//                // 但如果这两个字符分开显示时，会换两次行。
//                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
//                if (((char) tempchar) != '\r') {
//                    System.out.print((char) tempchar);
//                }
//            }
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字符
//            char[] tempchars = new char[30];
//            int charread = 0;
//            reader = new InputStreamReader(new FileInputStream(fileName));
//            // 读入多个字符到字符数组中，charread为一次读取字符数
//            while ((charread = reader.read(tempchars)) != -1) {
//                // 同样屏蔽掉\r不显示
//                if ((charread == tempchars.length)
//                        && (tempchars[tempchars.length - 1] != '\r')) {
//                    System.out.print(tempchars);
//                } else {
//                    for (int i = 0; i < charread; i++) {
//                        if (tempchars[i] == '\r') {
//                            continue;
//                        } else {
//                            System.out.print(tempchars[i]);
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }
//
//    /**
//     * 以行为单位读取文件，常用于读面向行的格式化文件
//     */
//    public static void readFileByLines(String fileName) {
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            int line = 1;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
//                line++;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }
//
//    /**
//     * 随机读取文件内容
//     */
//    public static void readFileByRandomAccess(String fileName) {
//        RandomAccessFile randomFile = null;
//        try {
//            System.out.println("随机读取一段文件内容：");
//            // 打开一个随机访问文件流，按只读方式
//            randomFile = new RandomAccessFile(fileName, "r");
//            // 文件长度，字节数
//            long fileLength = randomFile.length();
//            // 读文件的起始位置
//            int beginIndex = (fileLength > 4) ? 4 : 0;
//            // 将读文件的开始位置移到beginIndex位置。
//            randomFile.seek(beginIndex);
//            byte[] bytes = new byte[10];
//            int byteread = 0;
//            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
//            // 将一次读取的字节数赋给byteread
//            while ((byteread = randomFile.read(bytes)) != -1) {
//                System.out.write(bytes, 0, byteread);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (randomFile != null) {
//                try {
//                    randomFile.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }

    public static void writeFileByBytes() {
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
            bbytes[0]=(byte)251;
            bbytes[1]=(byte)251;
            bbytes[2]=(byte)251;
            bbytes[3]=(byte)251;
            fos = new FileOutputStream(file2);
            fos.write(bbytes);
            fos.close();
        }catch(Exception e){

        }

    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "C:/fjd/aa.bmp";
        readFileByBytes(fileName);
//        ReadFromFile.readFileByChars(fileName);
//        ReadFromFile.readFileByLines(fileName);
//        ReadFromFile.readFileByRandomAccess(fileName);
//        writeFileByBytes();
    }
}

