package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtil {
    public static void main(String[] args) throws IOException{
        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            pingSocket = new Socket("babycaptcha.xsec.sap.corp", 13337);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        System.out.println(in.readLine());
//        out.println("hi");
        while(true){
            char[] buffer = new char[100];
            in.read(buffer);
            String s = new String(buffer);
            System.out.println(s);
            int a = calcResult(s);
            System.out.println(a);
            out.println(a);
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
