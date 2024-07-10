package org.chatClient.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class SocketClient {
    static final  String host = "192.168.0.111";
    static final  int port = 8189;

    public String goOnSocketCient ( String command, String userName, String password) throws IOException, InterruptedException{
        String response= "upset";
        InetAddress adress = InetAddress.getByName(host);
        try (Socket s = new Socket(adress, port)) {
            s.setSoTimeout(1000000);

            InputStream inStream = s.getInputStream();
            BufferedReader brNet = new BufferedReader(new InputStreamReader(inStream));

            OutputStream outputStream = s.getOutputStream();
            PrintWriter outNet = new PrintWriter(outputStream, true);

            outNet.println(command);
            outNet.println(userName);
            outNet.println(password);

            if (brNet.ready()) {
                response=brNet.readLine().trim();
            }
        }
        return response;
    }
}
