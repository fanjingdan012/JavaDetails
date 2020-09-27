package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.EncodedKeySpec;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException{
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            socket = new Socket("10.55.128.202", 13337);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        System.out.println(in.readLine());

//        out.println("hi");
        while(true){
//            System.out.println(in.readLine());

            char[] buffer = new char[100];
            in.read(buffer);
            String s = new String(buffer);
            System.out.println(s);
            Scanner scan = new Scanner(System.in);
            if (scan.hasNext()) {
                String str1 = scan.next();
                System.out.println("input:" + str1);
                out.println(str1);
            }
//            int a = calcResult(s);
//            System.out.println(a);
//            out.println(a);
        }
//        out.close();
//        in.close();
//        pingSocket.close();
    }

    public static int calcResult(String s1){

        String s=s1.split("=")[0];
        if(s.contains("+")){
            String[] sp = s.split("\\+");
            return Integer.parseInt(sp[0])+Integer.parseInt(sp[1]);
        }
        if(s.contains("-")){
            String[] sp = s.split("\\-");
            return Integer.parseInt(sp[0])-Integer.parseInt(sp[1]);
        }
        if(s.contains("*")){
            String[] sp = s.split("\\*");
            return Integer.parseInt(sp[0])*Integer.parseInt(sp[1]);
        }
        if(s.contains("/")){
            String[] sp = s.split("/");
            return Integer.parseInt(sp[0])/Integer.parseInt(sp[1]);
        }
        return 0;



    }


}
