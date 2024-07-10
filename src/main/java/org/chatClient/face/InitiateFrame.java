package org.chatClient.face;

import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;
import org.chatClient.socket.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/** In this program organize GUI for initiation
 * for entering and registration users in chat.
 * If user catch a mistake, the response will
 * be written in the window.
 * */
public class InitiateFrame  extends JFrame {
    private static final int WINDOW_POS_X=100;
    private static final int WINDOW_POS_Y=100;

    JTextField name, password;
    JLabel reactionLabel;

    public InitiateFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setSize(MySizePanel.WIDTH_SIZE_WINDOW_INIT.getSize(),
                MySizePanel.HEIGHT_SIZE_WINDOW_INIT.getSize());
        setTitle("CHAT");

        Container contentPane = getContentPane();
        SpringLayout layout= new SpringLayout();
        contentPane.setLayout(layout);

        var nameLabel = new JLabel("Name", JLabel.RIGHT);
            nameLabel.setFont(MyFonts.FONT_LABEL.getFont());
        name = new JTextField( 15);
            name.setFont(MyFonts.FONT_PASSWORD.getFont());

        var passwordLabel = new JLabel("Password");
            passwordLabel.setFont(MyFonts.FONT_LABEL.getFont());
        password = new JTextField(15);
            password.setFont(MyFonts.FONT_PASSWORD.getFont());

        var buttonIn = new JButton("In account");
            buttonIn.setFont(MyFonts.FONT_BUTTON.getFont());
        var buttonRegistration = new JButton("Registration");
            buttonRegistration.setFont(MyFonts.FONT_BUTTON.getFont());

        reactionLabel = new JLabel(" ",JLabel.CENTER);
            reactionLabel.setFont(MyFonts.FONT_LABEL_REACT.getFont());

        contentPane.add(nameLabel);
        contentPane.add(name);
        contentPane.add(passwordLabel);
        contentPane.add(password);
        contentPane.add(buttonIn);
        contentPane.add(buttonRegistration);
        contentPane.add(reactionLabel);

        layout.putConstraint(SpringLayout.WEST , nameLabel, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 25, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, name, 25, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.EAST , name, -20, SpringLayout.EAST , contentPane );

        layout.putConstraint(SpringLayout.WEST , passwordLabel, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, nameLabel);

        layout.putConstraint(SpringLayout.NORTH, password, 20, SpringLayout.SOUTH, nameLabel);
        layout.putConstraint(SpringLayout.EAST , password, -20, SpringLayout.EAST , contentPane );

        layout.putConstraint(SpringLayout.NORTH, buttonRegistration, 30, SpringLayout.SOUTH, password);
        layout.putConstraint(SpringLayout.EAST , buttonRegistration, 0, SpringLayout.EAST , password );

        layout.putConstraint(SpringLayout.NORTH, buttonIn, 30, SpringLayout.SOUTH, password);
        layout.putConstraint(SpringLayout.EAST , buttonIn, -20, SpringLayout.WEST , buttonRegistration);

        layout.putConstraint(SpringLayout.WEST , reactionLabel, 35, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, reactionLabel, 35, SpringLayout.SOUTH, buttonRegistration);



        buttonIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSocket("in account");

            }
        });
        buttonRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSocket("registration");
            }
        });

        setResizable(false);
        setVisible(true);
    }


    void makeSocket(String command)  {
        SocketClient socketClient = new SocketClient();

        if (name.getText().trim().isEmpty()) {
            reactionLabel.setText("Enter the user name.");
            return;
        }
        if (name.getText().length()>15) {
            reactionLabel.setText("<html>   User name could not be longer<br>then 15 signs.</html>");
            return;
        }
        if (password.getText().trim().isEmpty()) {
            reactionLabel.setText("Enter the password.");
            return;
        }
        if (password.getText().length()>15) {
            reactionLabel.setText("<html>Password could not be longer<br>then 15 signs.</html>");
            return;
        }

        String response="";
            try {
                response=socketClient.goOnSocketCient(command,name.getText(),password.getText());
            } catch (IOException e) {
                reactionLabel.setText("Could not connect to server");
            } catch (InterruptedException e) {
                reactionLabel.setText("Could not connect to server");
            }

        if (response.equals("upset")){
            reactionLabel.setText("Don't have response from server");
            return;
        }

        switch (command){
            case "registration" ->{
                switch (response){
                    case "OK" ->{
                        reactionLabel.setText("The registration has been a success.");
                    }
                    case "NO" ->{
                        reactionLabel.setText("User name is occupied ");
                    }
                }
            }
            case "in account" ->{
                switch (response){
                    case "OK" ->{
                        setVisible(false);
                        dispose();
                        System.exit(0);

                        EventQueue.invokeLater(() -> {
                            new ChatFrame();
                        });
                    }
                    case "NO" ->{
                        reactionLabel.setText("User name is used at this time. ");
                    }
                }
            }
        }

    }
}
