package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketServer {
private static void process(Socket s) throws Exception {
    InputStream in = s.getInputStream();
    OutputStream out = s.getOutputStream();
    DataInputStream dis = new DataInputStream(in);
    DataOutputStream dos = new DataOutputStream(out);

    int id = dis.readInt();
    dos.writeInt(1);
    dos.writeUTF("Hello");
    dos.flush();
}
}

