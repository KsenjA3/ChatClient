package org.chatClient.face;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/** In this program organize GUI for initiation
 * for entering and registration users in chat.
 * If user catch a mistake, the response will
 * be written in the window.
 * */
@Log4j2
@Getter
@Setter
public class StartFrame extends JFrame {
    private static final int WINDOW_POS_X=100;
    private static final int WINDOW_POS_Y=100;
    static final  String host = "192.168.0.111";
    static final  int port = 8189;


    private JTextField userName;
    JPasswordField password;
    private JLabel reactionLabel;

    public StartFrame(){
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
        userName = new JTextField( 15);
            userName.setFont(MyFonts.FONT_PASSWORD.getFont());

        var passwordLabel = new JLabel("Password");
            passwordLabel.setFont(MyFonts.FONT_LABEL.getFont());
        password = new JPasswordField(15);
            password.setFont(MyFonts.FONT_PASSWORD.getFont());

        var buttonIn = new JButton("In account");
            buttonIn.setFont(MyFonts.FONT_BUTTON.getFont());
        var buttonRegistration = new JButton("Registration");
            buttonRegistration.setFont(MyFonts.FONT_BUTTON.getFont());

        reactionLabel = new JLabel(" ",JLabel.CENTER);
            reactionLabel.setFont(MyFonts.FONT_LABEL_REACT.getFont());

        contentPane.add(nameLabel);
        contentPane.add(userName);
        contentPane.add(passwordLabel);
        contentPane.add(password);
        contentPane.add(buttonIn);
        contentPane.add(buttonRegistration);
        contentPane.add(reactionLabel);

        layout.putConstraint(SpringLayout.WEST , nameLabel, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 25, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, userName, 25, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.EAST , userName, -20, SpringLayout.EAST , contentPane );

        layout.putConstraint(SpringLayout.WEST , passwordLabel, 10, SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, nameLabel);

        layout.putConstraint(SpringLayout.NORTH, password, 20, SpringLayout.SOUTH, nameLabel);
        layout.putConstraint(SpringLayout.EAST , password, -20, SpringLayout.EAST , contentPane );

        layout.putConstraint(SpringLayout.NORTH, buttonRegistration, 30, SpringLayout.SOUTH, password);
        layout.putConstraint(SpringLayout.EAST , buttonRegistration, 0, SpringLayout.EAST , password );

        layout.putConstraint(SpringLayout.NORTH, buttonIn, 30, SpringLayout.SOUTH, password);
        layout.putConstraint(SpringLayout.EAST , buttonIn, -20, SpringLayout.WEST , buttonRegistration);

        layout.putConstraint(SpringLayout.WEST , reactionLabel, 25, SpringLayout.WEST , contentPane);
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
       String response="";
       Socket socketClient;

       boolean mistakeInput =check_client_input ();
       if (mistakeInput) return;

        try {
            InetAddress adress = InetAddress.getByName(host);
            socketClient = new Socket(adress, port);

            response=startClient(socketClient,command,userName.getText(),password.getText());
            reaction_for_response_from_server (socketClient, command,  response);

        }  catch (IOException e) {
            reactionLabel.setText("Could not connect to server.");
            log.error(e);
        }
    }

    @SneakyThrows(InterruptedException.class)
     String startClient (Socket s, String command, String userName, String password) throws IOException {
        String response= "upset";

        try (   BufferedReader brNet = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter outNet = new PrintWriter(s.getOutputStream(), true)
        ){
            outNet.println("command:"+command);
            outNet.println("user:"+userName);
            outNet.println("message"+password);
            outNet.flush();

            s.setSoTimeout(1000000000);
            Thread.sleep(100);
            if (brNet.ready()) {
                response=brNet.readLine().trim();
            }
        }
        return response;
    }

    void reaction_for_response_from_server (Socket socketClient, String command, String response) throws IOException {

        if (response.equals("upset")){
            socketClient.close();
            log.info(" BufferedReader is never ready.");
            reactionLabel.setText("Don't have response from server.");
            return;
        }

        switch (command){
            case "registration" ->{
                switch (response){
                    case "OK" ->{
                        reactionLabel.setText("The registration has been a success.");
                    }
                    case "BUSY" ->{
                        reactionLabel.setText("User name is occupied.");
                    }
                }
                socketClient.close();
            }
            case "in account" ->{
                switch (response){
                    case "OK" ->{
// close window start
                        setVisible(false);
                        dispose();
//                        System.exit(0);

// launch window chatting
                        EventQueue.invokeLater(() -> {
                            new ChatFrame(socketClient);
                        });
                    }
                    case "NoUser" ->{
                        reactionLabel.setText("User name don't have a registration.");
                        socketClient.close();
                    }
                    case "NoPassword" ->{
                        reactionLabel.setText("Wrong password.");
                        socketClient.close();
                    }
                    case "BUSY" ->{
                        reactionLabel.setText("User name is used at this time.");
                        socketClient.close();
                    }
                }
            }
        }

    }

    boolean check_client_input (){
        if (StringUtils.isEmpty(userName.getText())) {
            reactionLabel.setText("Enter the user userName.");
            return true;
        }
        if (userName.getText().length()>15) {
            reactionLabel.setText("<html>   User name could not be longer<br>then 15 signs.</html>");
            return true;
        }
        if(StringUtils.isEmpty(password.getText())){
            reactionLabel.setText("Enter the password.");
            return true;
        }
        if (password.getText().length()>15) {
            reactionLabel.setText("<html>Password could not be longer<br>then 15 signs.</html>");
            return true;
        }

        return false;
    }
}
