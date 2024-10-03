package org.chatClient.face;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.chatClient.fittings.MyColors;
import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;

import javax.swing.*;
import java.awt.*;


/** In this program organize GUI for chat.
 *  JPanel for receiving  messages.
 * */
@Log4j2
class  PanelCorrespondence extends JPanel {
    private JFrame frame;
    private ChatFrame chatFrame;
    @Getter
    private JScrollPane scrollCorrespondence;
    private JPanel panel_oneMessage_inScroll;
    private JTextArea txtCorrespondence;
    private  JLabel labelSender;
    @Getter
    private JPanel paneRequestCorrespondence;
    private SpringLayout layout;
    @Getter
    private JComboBox< String> periodComboBox, personComboBox, typeMessageComboBox;
    private JLabel periodLabel, personLabel, typeMessageLabel;
    private JButton buttonNewMessage, buttonRequestCorrespondence;
    private Color colorButton;

    private  StringBuffer allTextCorrespondence;


    PanelCorrespondence(ChatFrame chatFrame) {
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;

    //панель с сообщениями
        panel_oneMessage_inScroll = new JPanel();
        panel_oneMessage_inScroll.setLayout(new BoxLayout(panel_oneMessage_inScroll,BoxLayout.PAGE_AXIS));
        scrollCorrespondence = new JScrollPane (panel_oneMessage_inScroll,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollCorrespondence.setBorder(null);
        scrollCorrespondence.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        scrollCorrespondence.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollCorrespondence.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_SCROLL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_SCROLL_CORRESPONDENCE.getSize()));

    //панель с настройками запроса
        paneRequestCorrespondence = new JPanel();
        layout= new SpringLayout();
        paneRequestCorrespondence.setLayout(layout);
        paneRequestCorrespondence.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_REQUEST_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_REQUEST_CORRESPONDENCE.getSize()));
//        paneRequestCorrespondence.setBorder(BorderFactory.createLineBorder(Color.blue));


        buttonRequestCorrespondence= new JButton("Receive");
            buttonRequestCorrespondence.setText("<html><center>"+"Receive"+"</center></html>");
            buttonRequestCorrespondence.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonRequestCorrespondence.setPreferredSize(new Dimension(70,30));
            colorButton= buttonRequestCorrespondence.getBackground();

        buttonNewMessage= new JButton("New");
            buttonNewMessage.setText("<html><center>"+"New"+"<br>"+"Messages"+"</center></html>");
            buttonNewMessage.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonNewMessage.setPreferredSize(new Dimension(80,80));
            buttonNewMessage.setBackground(MyColors.COLOR_NEW_MESSAGES.getColor());
            buttonNewMessage.setBackground(colorButton);

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
            periodComboBox.addItem("all time");
            periodComboBox.addItem("for week");
            periodComboBox.addItem("for month");
            periodComboBox.addItem("for year");
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

    // fitting panelCorrespondence
//        setSize(MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
//                MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize());

        setLayout(new BorderLayout());
        add(scrollCorrespondence, BorderLayout.SOUTH);
        add(paneRequestCorrespondence, BorderLayout.NORTH);



        //        allTextCorrespondence= new StringBuffer();
        for (int i =1; i<20; i++){
            String sender = " somebody";
            String message = "text text text text text text text text text text text text text text" +
                    "text text text text text text text text text text text text text text text text  --  ";
            print_one_correspondence(sender, message);
        }
    }

    void setLabelCorrespondence (String sender,String text) {
        allTextCorrespondence.append("from: ");
        allTextCorrespondence.append(sender);
        allTextCorrespondence.append(":/n");
        allTextCorrespondence.append(text);
        allTextCorrespondence.append("/n");
        allTextCorrespondence.append("/n");

//        labelCorrespondence.setText(allTextCorrespondence.toString());
    }

    private void print_one_correspondence(String sender, String message){
        //панель информации об отправителе
        JPanel panel_info_user = new JPanel(new BorderLayout());
        panel_info_user.setSize(MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize());

        labelSender= new JLabel();
        labelSender.setFont(MyFonts.FONT_LABEL_SENDER_CORRESPONDENCE.getFont());
        labelSender.setBackground(MyColors.COLOR_LABEL_SENDER_CORRESPONDENCE_BACKGROUND.getColor());
        labelSender.setText(sender);

        panel_info_user.add(labelSender, BorderLayout.WEST);

        txtCorrespondence = new JTextArea();
        txtCorrespondence.setBackground(MyColors.COLOR_AREA_MESSAGE_BACKGROUND.getColor());
        txtCorrespondence.setFont(MyFonts.FONT_TEXT_CORRESPONDENCE.getFont());
        txtCorrespondence.setLineWrap(true);
        txtCorrespondence.setWrapStyleWord(true);
        txtCorrespondence.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
//        txtCorrespondence.setPreferredSize(new Dimension(320,50));
        txtCorrespondence.setText(message);

        panel_oneMessage_inScroll.add(panel_info_user);
        panel_oneMessage_inScroll.add(txtCorrespondence);
        panel_oneMessage_inScroll.add(Box.createRigidArea(new Dimension(2, 8)));
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

        paneRequestCorrespondence.add(personComboBox);

    }
}
