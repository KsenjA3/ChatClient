package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.MyColors;
import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** In this program organize GUI for chat.
 *  JPanel for receiving  messages.
 * */
@Log4j2
class  PanelCorrespondence extends JPanel {
    private JFrame frame;
    private ChatFrame chatFrame;
    private SendMessage sendMessage;
    @Getter
    private JScrollPane scrollCorrespondence;
    @Getter
    private JPanel paneRequestCorrespondence;
    @Getter
    private JComboBox< String> periodComboBox, personComboBox, typeMessageComboBox;
    @Getter
    private JButton buttonNewMessage;
    private JLabel periodLabel, personLabel, typeMessageLabel;
    private JButton buttonRequestCorrespondence;
    private Color colorButton;
    private SpringLayout layout;
    private JPanel panel_oneMessage_inScroll;
    private JTextArea txtCorrespondence;
    private  JLabel labelSender;

    PanelCorrespondence(ChatFrame chatFrame) {
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        sendMessage = new SendMessage(chatFrame);

        //панель с сообщениями
        panel_oneMessage_inScroll = new JPanel();
        panel_oneMessage_inScroll.setLayout(new BoxLayout(panel_oneMessage_inScroll,BoxLayout.PAGE_AXIS));
        scrollCorrespondence = new JScrollPane (panel_oneMessage_inScroll,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollCorrespondence.setBorder(null);
        scrollCorrespondence.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        scrollCorrespondence.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollCorrespondence.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_SCROLL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_SCROLL_CORRESPONDENCE.getSize()));

        //панель с настройками запроса
        paneRequestCorrespondence = new JPanel();
        layout= new SpringLayout();
        paneRequestCorrespondence.setLayout(layout);
        //paneRequestCorrespondence.setBorder(BorderFactory.createLineBorder(Color.blue));

        buttonRequestCorrespondence= new JButton("Receive");
            buttonRequestCorrespondence.setText("<html><center>"+"Receive"+"</center></html>");
            buttonRequestCorrespondence.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonRequestCorrespondence.setPreferredSize(new Dimension(70,30));
            colorButton= buttonRequestCorrespondence.getBackground();
        buttonRequestCorrespondence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send_request_correspondence ();
            }
        });

        buttonNewMessage= new JButton("New");
            buttonNewMessage.setText("<html><center>"+"New"+"<br>"+"Messages"+"</center></html>");
            buttonNewMessage.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonNewMessage.setPreferredSize(new Dimension(80,80));
            buttonNewMessage.setBackground(colorButton);
        buttonNewMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeMessageComboBox.setSelectedItem("unread");
                periodComboBox.setSelectedItem("all");
                personComboBox.setSelectedItem("all");
                buttonNewMessage.setBackground(colorButton);
                send_request_correspondence ();
                frame.pack();
            }
        });
        typeMessageLabel = new JLabel("Messages:");
            typeMessageLabel.setSize(90,20);
            typeMessageLabel.setFont(MyFonts.FONT_LABEL_CORRESPONDENCE.getFont());
        typeMessageComboBox=new JComboBox<>();
            typeMessageComboBox.addItem("all");
            typeMessageComboBox.addItem("received");
            typeMessageComboBox.addItem("sent");
            typeMessageComboBox.addItem("unread");
            typeMessageComboBox.setPreferredSize(new Dimension(100,20));
            typeMessageComboBox.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());

        personLabel = new JLabel("Collocutor:");
            personLabel.setSize(90,20);
            personLabel.setFont(MyFonts.FONT_LABEL_CORRESPONDENCE.getFont());
        fill_personComboBox();

        periodLabel = new JLabel("Time period:");
            periodLabel.setPreferredSize(new Dimension(90,20));
            periodLabel.setFont(MyFonts.FONT_LABEL_CORRESPONDENCE.getFont());
        periodComboBox=new JComboBox<>();
            periodComboBox.addItem("for week");
            periodComboBox.addItem("for month");
            periodComboBox.addItem("for year");
        periodComboBox.addItem("all time");
            periodComboBox.setPreferredSize(new Dimension(100,20));
            periodComboBox.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());

        paneRequestCorrespondence.add(buttonNewMessage);
        paneRequestCorrespondence.add(typeMessageLabel);
        paneRequestCorrespondence.add(typeMessageComboBox);
        paneRequestCorrespondence.add(personLabel);
        paneRequestCorrespondence.add(periodLabel);
        paneRequestCorrespondence.add(periodComboBox);
        paneRequestCorrespondence.add(buttonRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , buttonNewMessage, 10, SpringLayout.WEST , paneRequestCorrespondence);
        layout.putConstraint(SpringLayout.NORTH , buttonNewMessage, 10, SpringLayout.NORTH , paneRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , typeMessageLabel, 10, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , typeMessageLabel, 10, SpringLayout.NORTH , paneRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , typeMessageComboBox, 100, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , typeMessageComboBox, 10, SpringLayout.NORTH , paneRequestCorrespondence);


        layout.putConstraint(SpringLayout.WEST , personLabel, 10, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , personLabel, 40, SpringLayout.NORTH , paneRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , periodLabel, 10, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , periodLabel, 70, SpringLayout.NORTH , paneRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , periodComboBox, 100, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , periodComboBox, 70, SpringLayout.NORTH , paneRequestCorrespondence);

        layout.putConstraint(SpringLayout.WEST , buttonRequestCorrespondence, 210, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , buttonRequestCorrespondence, 35, SpringLayout.NORTH , paneRequestCorrespondence);

        //fitting panelCorrespondence
        setLayout(new BorderLayout());
        add(scrollCorrespondence, BorderLayout.SOUTH);
        add(paneRequestCorrespondence, BorderLayout.NORTH);

//        for (int i =1; i<20; i++){
//            String sender = "<html>FROM userSendRequest3: on 17.10.2024 at 14.20.21<br>(familiarized on 17.10.2024 at 14.20.21)</html>)";
//            String message = "text text text text text text text text text text text text text text" +
//                    "text text text text text text text text text text text text text text text text  --  ";
//            print_one_correspondence(sender, message);
//        }
    }

    protected void print_one_correspondence(String sender, String message){
        //панель информации об отправителе
        JPanel panel_info_user = new JPanel(new BorderLayout());
//        panel_info_user.setPreferredSize(new Dimension( MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(), 30));

        labelSender= new JLabel();
        labelSender.setFont(MyFonts.FONT_LABEL_SENDER_CORRESPONDENCE.getFont());
        labelSender.setBackground(MyColors.COLOR_LABEL_SENDER_CORRESPONDENCE_BACKGROUND.getColor());
        labelSender.setText(sender);
//        labelSender.setPreferredSize(new Dimension( MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize()-10,  20));

        panel_info_user.add(labelSender, BorderLayout.CENTER);

        txtCorrespondence = new JTextArea();
        txtCorrespondence.setBackground(MyColors.COLOR_AREA_MESSAGE_BACKGROUND.getColor());
        txtCorrespondence.setFont(MyFonts.FONT_TEXT_CORRESPONDENCE.getFont());
        txtCorrespondence.setLineWrap(true);
        txtCorrespondence.setWrapStyleWord(true);
        txtCorrespondence.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        txtCorrespondence.setText(message);
//        txtCorrespondence.setPreferredSize(new Dimension( MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize()-10,10));

        panel_oneMessage_inScroll.add(panel_info_user);
        panel_oneMessage_inScroll.add(txtCorrespondence);
        panel_oneMessage_inScroll.add(Box.createRigidArea(new Dimension(2, 4)));
    }

    void fill_personComboBox() {
        personComboBox=new JComboBox<>();
            personComboBox.setPreferredSize(new Dimension(100,20));
            personComboBox.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());
            personComboBox.addItem("all");
            chatFrame.getReferenceBook().forEach((user,online)->{
                String str ;
                if (online){
                    str=user +" (online)";
                }else {
                    str=user;
                }
                personComboBox.addItem(str);
            });

        layout.putConstraint(SpringLayout.WEST , personComboBox, 100, SpringLayout.EAST , buttonNewMessage);
        layout.putConstraint(SpringLayout.NORTH , personComboBox, 40, SpringLayout.NORTH , paneRequestCorrespondence);

        paneRequestCorrespondence.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_REQUEST_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_REQUEST_CORRESPONDENCE.getSize()));
        setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize()));
        paneRequestCorrespondence.add(personComboBox);
    }

    private void send_request_correspondence (){
        RequestCorrespondence requestCorrespondence = RequestCorrespondence.builder()
                .type(typeMessageComboBox.getItemAt(typeMessageComboBox.getSelectedIndex()))
                .period(periodComboBox.getItemAt(periodComboBox.getSelectedIndex()))
                .collocutor(personComboBox.getItemAt(personComboBox.getSelectedIndex()))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String contentMessage= null;
        try {
            contentMessage = objectMapper.writeValueAsString(requestCorrespondence);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String commandMessage = "request_correspondence";
        sendMessage.sendMes(commandMessage,contentMessage);
    }
}
