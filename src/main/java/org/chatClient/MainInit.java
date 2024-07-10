package org.chatClient;

import org.chatClient.face.ChatFrame;
import org.chatClient.face.InitiateFrame;

import java.awt.*;

public class MainInit {
    public static void main(String[] args)  {

        EventQueue.invokeLater(() -> {
            new InitiateFrame();
        });
    }
}