package hash.md5;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MazeSolver {
    private static int RED = -1237980;
    private static int WHITE = -1;
    private static final int BLACK = -16777216;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        File file2 = new File("C:\\fjd\\ctf\\amazing1.bmp");
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
                    if (tempbyte == 10) { bbytes[i - 1] = (byte) 0; }
                    if (tempbyte == 7) { bbytes[i - 1] = (byte) 0; }
                    if (tempbyte == 9) { bbytes[i - 1] = (byte) 0; }
                    if (tempbyte == 0) { bbytes[i - 1] = (byte) 255; }
                    //System.out.print(tempbyte+",");
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


    private ArrayList<Point> solveMaze(BufferedImage image) {
        Point p = new Point(1, 1);
        p.setTriedDirection(0);
        ArrayList<Point> points = new ArrayList<>();
        points.add(p);
        while (p.getX() != 3999 || p.getY() != 3999) {

            int direction = goOneStep(image, p);
            if (direction == 5) {
                p = points.get(points.size() - 1);
                image.setRGB(p.getX(),p.getY(),WHITE);
                points.remove(points.size() - 1);

            } else{
                p.setTriedDirection(direction);
                Point newP = getNewPointByDirection(p,direction);
                newP.setTriedDirection(0);
                points.add(newP);

            }
            p=points.get(points.size() - 1);


        }
        return points;
    }

    public static void main(String[] args) {
        //String fileName = "C:/fjd/ctf/amazing.bmp";
        //readFileByBytes(fileName);
        try {
            BufferedImage image = ImageIO.read(new File("C:/fjd/ctf/amazing.bmp"));
//            int rgb = image.getRGB(1, 1);
//            System.out.print(rgb);
//            image.setRGB(1, 1, -1237980);
            ArrayList<Point> points = new MazeSolver().solveMaze(image);

            for(int i = 0;i<points.size();i++){
                System.out.println("("+points.get(i).getX()+", "+points.get(i).getY()+"),"+points.get(i).getTriedDirection());
            }
            int i = 0;
            int lastDirection = 0;
            int direction;
            do{
                Point p = points.get(i);
                direction = p.getTriedDirection();
                if(direction!=lastDirection){

                    System.out.println("("+p.getX()+", "+p.getY()+")");
                    lastDirection = direction;
                }
                i++;
            }while(i<points.size());
            //md5(str([(x, y), (x, y), ..., (3999, 3999)]))

            ImageIO.write(image, "bmp", new File("C:/fjd/ctf/amazing2.bmp"));



            //char[][] img2 = new char[4001][4001];
            //            for(int i = 0;i<4001;i++){
            //                for(int j = 0;j<4001;j++){
            //                    int rgb = image.getRGB(i,j);
            //                    if(rgb == -16777216){
            //                        System.out.print("黑");
            //                    }else if(rgb==-1){
            //                        System.out.print("白");
            //                    }else{
            //                        System.out.print("别");
            //                    }
            //                }
            //                System.out.println();
            //            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        ReadFromFile.readFileByChars(fileName);
        //        ReadFromFile.readFileByLines(fileName);
        //        ReadFromFile.readFileByRandomAccess(fileName);
        //        writeFileByBytes();
    }

    private Point getNewPointByDirection(Point from, int direction) {
        if (direction == UP) {
            return new Point(from.getX(),from.getY()-1);
        }
        if (direction == DOWN) {
            return new Point(from.getX(),from.getY()+1);
        }
        if (direction == LEFT) {
            return new Point(from.getX()-1,from.getY());
        }
        if (direction == RIGHT) {
            return new Point(from.getX()+1,from.getY());
        }
        return null;

    }



    private int goOneStep(BufferedImage image, Point p) {
        int x = p.getX();
        int y = p.getY();
        int triedDirection = p.getTriedDirection();
        while (triedDirection < 5) {
            triedDirection++;
            //go up
            if (triedDirection == UP) {
                if (image.getRGB(x, y - 1) == WHITE) {

                    image.setRGB(x, y - 1, RED);
                    return UP;
                }
            }
            //go down
            if (triedDirection == DOWN) {
                if (image.getRGB(x, y + 1) == WHITE) {
                    image.setRGB(x, y + 1, RED);
                    return DOWN;
                }
            }
            //go right
            if (triedDirection == RIGHT) {
                if (image.getRGB(x + 1, y) == WHITE) {
                    image.setRGB(x + 1, y, RED);
                    return RIGHT;
                }
            }

            //go left
            if (triedDirection == LEFT) {
                if (image.getRGB(x - 1, y) == WHITE) {
                    image.setRGB(x - 1, y, RED);
                    return LEFT;
                }
            }
        }

        return 5;

    }
}


class Point {
    int x;
    int y;
    int triedDirection;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTriedDirection() {
        return triedDirection;
    }

    public void setTriedDirection(int triedDirection) {
        this.triedDirection = triedDirection;
    }
}

