package net;

import java.io.*;
import java.net.*;

public class TelnetClient {

    public static String readAllLines(BufferedReader in, int x) throws Exception {
        String result = "";
        String s;
        for (int i = 0; i < x; i++) {
            s = in.readLine();
            System.out.println("response from server:" + s);
        }
        // while((s=in.readLine())!=null){
        // System.out.println("response from server:"+s);
        // result+=s+"\n";
        // }
        // while (true) {
        // String s= in.readLine();
        // if(s==null){
        // break;
        // }
        // result+=s;
        //
        // }
        return result;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[3113421];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    public static void main(String[] args) throws Exception {

        // The args should be the server host name
        Socket telnetSocket = null;
        PrintWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        String host = null;

        // if (args!=null && args.length>0){
        // if (args[0]!=null && !args[0].equals("")){
        host = "localhost";// args[0];
        try {
            telnetSocket = new Socket(host, 9999);// 23
            out = new PrintWriter(telnetSocket.getOutputStream(), true);
            in = telnetSocket.getInputStream();

            br = new BufferedReader(new InputStreamReader(in));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: " + host + ".");
            System.exit(1);
        }
        System.out.println("server is : " + host);
        System.out.println("server socket connected! " + telnetSocket.toString());
        System.out.println("available:" + in.available());
        String s = readAllLines(br, 9);
        System.out.println("response from server:\n" + s);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            if ("HELP".equals(userInput)) {
                s = readAllLines(br, 9);
            } else if ("GET_DATA".equals(userInput)) {
                byte[] bt = readStream(telnetSocket.getInputStream());
                writeFile(bt);
            } else {
                s = readAllLines(br, 1);
            }

            System.out.println("response from server:\n" + s);
        }

        out.close();
        br.close();
        stdIn.close();
        telnetSocket.close();
    }
    // }
    // }

    public static void writeFile(byte[] bytes) {

        File file2 = new File("./b.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file2);
            fos.write(bytes);

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
