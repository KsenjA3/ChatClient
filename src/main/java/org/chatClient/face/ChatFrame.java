package org.chatClient.face;

import javax.swing.*;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.MySizePanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

/** In this program organize GUI for chat.
 * In JFrame are situated two JPanels:
 * for receiving and for sending messages.
 * */
@Log4j2
public class ChatFrame extends JFrame{
    private static final int WINDOW_POS_X=100;
    private static final int WINDOW_POS_Y=100;

    PanelCorrespondence panelCorrespondence;
    PanelMessage panelMessage;
    Socket socketClient;
    String nameUser;

    public ChatFrame(Socket s, String userName){
        socketClient=s;
        nameUser=userName;

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @SneakyThrows
            public void windowClosing(WindowEvent e) {
                PrintWriter outNet = new PrintWriter(socketClient.getOutputStream(), true);
                outNet.println("command:"+"exit");
                outNet.println("user:"+nameUser);

//        close socket
                socketClient.close();
//        close window
                setVisible(false);
                dispose();
                System.exit(0); //calling the method is a must
            }
        });



        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setSize(MySizePanel.WIDTH_SIZE_WINDOW.getSize(), MySizePanel.HEIGHT_SIZE_WINDOW.getSize());
        setTitle("CHAT");
        panelCorrespondence = new PanelCorrespondence();
        panelMessage=new PanelMessage(panelCorrespondence, this);

        add(panelCorrespondence, BorderLayout.CENTER);
        add(panelMessage, BorderLayout.SOUTH);

//        setResizable(false);
        setVisible(true);
    }

}
