package org.chatClient;

import org.chatClient.face.ChatFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args)  {

        EventQueue.invokeLater(() -> {
            new ChatFrame();
        });
    }
}