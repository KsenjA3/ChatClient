package org.chatClient.face;

import javax.swing.*;
import lombok.extern.log4j.Log4j2;
import org.chatClient.fittings.MySizePanel;

import java.awt.*;
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

    public ChatFrame(Socket s){
        this.socketClient=s;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        закрыть в конце
//        socketClient.close();

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
