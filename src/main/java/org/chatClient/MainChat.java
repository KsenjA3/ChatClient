package org.chatClient;

import lombok.extern.log4j.Log4j2;
import org.chatClient.face.ChatFrame;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Log4j2
public class MainChat {
    static final  String host = "192.168.0.111";
    static final  int port = 8189;

    public static void main(String[] args)  {
        Socket socketClient = null;
        try {
            InetAddress adress = InetAddress.getByName(host);
            socketClient = new Socket(adress, port);

            EventQueue.invokeLater(() -> {
                new ChatFrame(socketClient);
            });
        } catch (IOException e) {
            log.error(e);
        }

    }
}