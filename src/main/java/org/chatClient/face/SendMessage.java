package org.chatClient.face;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class SendMessage {
    private Socket socket;
    private String userName;
    private JFrame frame;
    private PrintWriter outNet;
    private boolean done;
    private ChatFrame chatFrame;

    SendMessage( ChatFrame chatFrame){
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        socket=chatFrame.getSocketClient();
        userName=chatFrame.getNameUser();

    }
    void sendMes (String command, String message)  {


        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("command:"+ command);
            out.println("user:"+userName);
            String str = "message:"+ message.replaceAll("\n", "<br>");;
            out.println(str);

            System.out.println();
            System.out.println("---Send---:");
            System.out.println("1command- "+"command:"+ command);
            System.out.println("2user- "+"user:"+userName);
            System.out.println("3message- "+str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
