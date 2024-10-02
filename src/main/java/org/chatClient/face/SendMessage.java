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
    void sendMes (String toUser, String message)  {

        toUser= StringUtils.remove(toUser,"(online)").trim();
        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.println("command:chattingTo:"+ toUser);
            out.println("user:"+userName);
            String str = "message:"+ message.replaceAll("\n", "<br>");;
            out.println(str);

//            socket.setSoTimeout(1000000000);
//            Thread.sleep(100);
//            if (br.ready()) {
//                String response_command=br.readLine().trim();
//                String response_user=br.readLine().trim();
//                String response_message=br.readLine().trim();
//                if ()
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
