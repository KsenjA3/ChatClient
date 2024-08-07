package org.chatClient;

import javax.swing.*;

public class Example {
    public static void main(String[] args) {
        var panel= new JTextField();
        if ( panel.getText().trim().isEmpty() )
            System.out.println("true");
        else
            System.out.println("false");
    }
}
