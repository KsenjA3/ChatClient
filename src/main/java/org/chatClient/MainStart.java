package org.chatClient;

import org.chatClient.face.StartFrame;

import java.awt.*;

public class MainStart {
    public static void main(String[] args)  {

        EventQueue.invokeLater(() -> {
            new StartFrame();
        });
    }
}