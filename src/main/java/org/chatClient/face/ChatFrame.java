package org.chatClient.face;

import javax.swing.*;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.MySizePanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentMap;

/** In this program organize GUI for chat.
 * In JFrame are situated two JPanels:
 * for receiving and for sending messages.
 * */
@Log4j2
public class ChatFrame {
    private static final int WINDOW_POS_X=100;
    private static final int WINDOW_POS_Y=100;

    @Getter
    JFrame frame;
    @Getter
    private PanelCorrespondence panelCorrespondence;
    @Getter
    private PanelMessage panelMessage;
    @Getter
    private Socket socketClient;
    @Getter
    private String nameUser;

    @SneakyThrows (InterruptedException.class)
    public ChatFrame(Socket s, String userName){
        socketClient=s;
        nameUser=userName;
        frame=new JFrame();

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @SneakyThrows
            public void windowClosing(WindowEvent e) {
                PrintWriter outNet = new PrintWriter(socketClient.getOutputStream(), true);
                outNet.println("command:"+"exit");
                outNet.println("user:"+nameUser);
                outNet.println("message:"+"exit");

//        close socket
                socketClient.close();
//        close window
                frame.setVisible(false);
                frame.dispose();
                System.exit(0); //calling the method is  must
            }
        });



        frame.setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        frame.setSize(MySizePanel.WIDTH_SIZE_WINDOW.getSize(), MySizePanel.HEIGHT_SIZE_WINDOW.getSize());
        frame.setTitle("CHAT");
        panelCorrespondence = new PanelCorrespondence(this);
        panelMessage=new PanelMessage(this);

        Runnable r = new ReceiveMessage(this);
        Thread t = new Thread(r);
        t .start ();
        Thread.sleep(1);

        frame.add(panelCorrespondence, BorderLayout.CENTER);
        frame.add(panelMessage, BorderLayout.SOUTH);




//        setResizable(false);
        frame.setVisible(true);
    }

void repack(){
        frame.invalidate();
        frame.validate();
        frame.revalidate();
//         frame.repaint();
    frame.pack();
}

}
