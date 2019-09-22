package file;

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

