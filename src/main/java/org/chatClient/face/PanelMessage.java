package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;


/** In this program organize GUI for chat.
 *  JPanel for sending  messages.
 * */
@Log4j2
class PanelMessage extends JPanel {
    ChatFrame chatFrame;
    @Getter
    private JComboBox< String> receiverComboBox;
    @Getter
    private JPanel panelReceiver;
    @Getter
    private JPanel panelMessage;
    @Getter
    @Setter
    private JTextArea textAreaMessage;

    private String nameUser;
    private SendMessage sendMessage;
    private JFrame frame;
    private PanelCorrespondence panelCorrespondence;
    private JButton buttonSend, buttonMinus, buttonPlus;
    private JLabel receiverList, infoLabel;
    private JScrollPane scrollMessage, scrollReceiverList;
    private String contentMessage, receiverOfMessage;
    private String reseiversString;
    private SpringLayout layout;
    PanelMessage( ChatFrame chatFrame){
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        nameUser= chatFrame.getNameUser();
        panelCorrespondence=chatFrame.getPanelCorrespondence();
        sendMessage =new SendMessage(chatFrame);

        buttonSend = new JButton("Send");
            buttonSend.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonSend.setSize(new Dimension(60,30));
            buttonSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentMessage= getMessage();

                    String receiverOfMessage=receiverList.getText();
                    if (receiverOfMessage.equals("")){
                        infoLabel.setText("<html>Message have't sent. Fill receiver list.</html>");
                        infoLabel.setForeground(Color.RED);
                        frame.pack();
                        return;
                    }
                    if (contentMessage.equals("")){
                        infoLabel.setText("<html>Message have't sent. Fill content.</html>");
                        infoLabel.setForeground(Color.RED);
                        frame.pack();
                        return;
                    }
                    while (StringUtils.contains(receiverOfMessage,"(online)"))
                        receiverOfMessage= StringUtils.remove(receiverOfMessage," (online)");
                    String commandMessage = "chattingTo:"+ receiverOfMessage;

                    sendMessage.sendMes(commandMessage,contentMessage);

                    textAreaMessage.setText("");
                    receiverList.setText("");
                    infoLabel.setText("<html>Message have sent successfully.</html>");
                    infoLabel.setForeground(Color.BLUE);
                    frame.pack();
                }
            });

        textAreaMessage= new JTextArea(7, 21);
        textAreaMessage.setBackground(MyColors.COLOR_AREA_MESSAGE_BACKGROUND.getColor());
        textAreaMessage.setFont(MyFonts.FONT_AREA.getFont());
        textAreaMessage.setLineWrap(true);
        textAreaMessage.setWrapStyleWord(true);

        scrollMessage = new JScrollPane (textAreaMessage,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        buttonPlus = new JButton("+");
            buttonPlus.setFont(MyFonts.FONT_BUTTON_MESSAGE.getFont());
//            buttonPlus.setBounds(1, 1, 150, 150);
//            buttonPlus.setPreferredSize(new Dimension(20,20));
            buttonPlus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reseiversString=receiverComboBox.getItemAt(receiverComboBox.getSelectedIndex());

                    if (reseiversString.equals("to all")){
                        receiverList.setText("<html>to all</html>");
                        frame.pack();
                        return;
                    }

                    String txtLabel= receiverList.getText();

                    if (txtLabel.equals("<html></html>")){
                        receiverList.setText("<html>"+ reseiversString +"</html>");
                        frame.pack();
                        return;
                    }
                    txtLabel= StringUtils.remove(txtLabel,"<html>to all</html>");
                    txtLabel= StringUtils.removeEnd(txtLabel,"</html>");

                    if (StringUtils.startsWith(txtLabel,"<html>")){
                        if (StringUtils.contains(txtLabel,reseiversString))     return;
                        txtLabel= StringUtils.removeEnd(txtLabel,"<br>");
                        txtLabel= txtLabel+"<br>"+reseiversString;
                    }
                    else {
                        txtLabel="<html>"+reseiversString;
                    }
                    txtLabel=txtLabel+"</html>";

                    receiverList.setText(txtLabel);
                    frame.pack();
                }
            });

        buttonMinus = new JButton("-");
            buttonMinus.setFont(MyFonts.FONT_BUTTON_MESSAGE.getFont());
            buttonMinus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reseiversString=receiverComboBox.getItemAt(receiverComboBox.getSelectedIndex());
                    String txtLabel= receiverList.getText();

                    txtLabel= StringUtils.remove(txtLabel,reseiversString+"<br>").trim();
                    txtLabel= StringUtils.remove(txtLabel,reseiversString);

                    System.out.println("---"+txtLabel);
                    receiverList.setText(txtLabel);
                    frame.pack();
                }
            });


        receiverList = new JLabel();
            receiverList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            receiverList.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());
        scrollReceiverList = new JScrollPane (receiverList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollReceiverList.setPreferredSize(new Dimension(MySizePanel.WIDTH_SIZE_PANEL_RECEIVER_COMBOBOX.getSize()+20,40));

        JLabel docketLabel = new JLabel("<html>Receiver<br>List:</html>");
            docketLabel.setFont(MyFonts.FONT_BUTTON_MESSAGE.getFont());

        infoLabel = new JLabel();
            infoLabel.setPreferredSize(new Dimension(80,80));
            infoLabel.setFont(MyFonts.FONT_BUTTON_MESSAGE.getFont());
            infoLabel.setHorizontalAlignment(JLabel.CENTER);
            infoLabel.setVerticalAlignment(JLabel.CENTER);

        panelReceiver= new JPanel(new BorderLayout());
            layout= new SpringLayout();
            panelReceiver.setLayout(layout);
            panelReceiver.add(buttonMinus);
            panelReceiver.add(buttonPlus);
            panelReceiver.add(scrollReceiverList);
            panelReceiver.add(docketLabel);
            panelReceiver.add(infoLabel);

        fill_receiverBook();

        layout.putConstraint(SpringLayout.WEST , docketLabel, 20, SpringLayout.WEST , panelReceiver);
        layout.putConstraint(SpringLayout.NORTH , docketLabel, 10, SpringLayout.NORTH , panelReceiver);

        layout.putConstraint(SpringLayout.EAST , scrollReceiverList, 0, SpringLayout.EAST , buttonMinus);
        layout.putConstraint(SpringLayout.NORTH , scrollReceiverList, 10, SpringLayout.NORTH , panelReceiver);

        layout.putConstraint(SpringLayout.WEST , infoLabel, 10, SpringLayout.EAST , buttonMinus);
        layout.putConstraint(SpringLayout.NORTH , infoLabel, 10, SpringLayout.NORTH , panelReceiver);


        layout.putConstraint(SpringLayout.WEST , buttonPlus, 30, SpringLayout.EAST , receiverComboBox);
        layout.putConstraint(SpringLayout.NORTH , buttonPlus, 6, SpringLayout.SOUTH , scrollReceiverList);

        layout.putConstraint(SpringLayout.WEST , buttonMinus, 10, SpringLayout.EAST , buttonPlus);
        layout.putConstraint(SpringLayout.NORTH , buttonMinus, 6, SpringLayout.SOUTH , scrollReceiverList);


        panelMessage= new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelMessage.add(scrollMessage);
            panelMessage.add(buttonSend);
        panelMessage.setSize(new Dimension(MySizePanel.WIDTH_SIZE_PANEL_SEND.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_SEND.getSize()));

        setLayout(new BorderLayout());
        add(panelReceiver,BorderLayout.NORTH);
        add(panelMessage, BorderLayout.CENTER);

        setSize(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize());
    }



    protected String getMessage (){
        return textAreaMessage.getText();
    }



    void fill_receiverBook() {
        receiverComboBox=new JComboBox<>();
        receiverComboBox.setPreferredSize(new Dimension(MySizePanel.WIDTH_SIZE_PANEL_RECEIVER_COMBOBOX.getSize(),
                                                        MySizePanel.HEIGHT_SIZE_PANEL_RECEIVER_COMBOBOX.getSize()));
        receiverComboBox.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());
        receiverComboBox.addItem("to all");
//        receiver.addItem("to all online");

        log.info("!!!{} -set  reference Book Client " ,chatFrame.getReferenceBook());
        chatFrame.getReferenceBook().forEach((user,online)->{
            String str ;
            if (online){
                str=user +" (online)";
            }else {
                str=user;
            }

            receiverComboBox.addItem(str);
        });

        panelReceiver.add(receiverComboBox);

        layout.putConstraint(SpringLayout.WEST , receiverComboBox, 20, SpringLayout.WEST , panelReceiver);
        layout.putConstraint(SpringLayout.NORTH , receiverComboBox, 6, SpringLayout.SOUTH , scrollReceiverList);

        panelReceiver.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_PANEL_RECEIVER.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_RECEIVER.getSize()));
        setPreferredSize(new Dimension(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize()));
    }

}
