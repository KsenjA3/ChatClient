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
    private JComboBox< String> receiver;
    @Getter
    private JPanel panelMessage;
    @Getter
    @Setter
    private JTextArea textAreaMessage;

    private String nameUser;
    private SendMessage sendMessage;
    private JFrame frame;
    private PanelCorrespondence panelCorrespondence;
    private JButton buttonSend;
    private JScrollPane scrollMessage;
    private String contentMessage, receiverOfMessage;

    PanelMessage( ChatFrame chatFrame){
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        nameUser= chatFrame.getNameUser();
        panelCorrespondence=chatFrame.getPanelCorrespondence();
        sendMessage =new SendMessage(chatFrame);

        buttonSend = new JButton("Send");
            buttonSend.setFont(MyFonts.FONT_BUTTON_CORRESPONDENCE.getFont());
            buttonSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentMessage= getMessage();
                    String receiverOfMessage=getSelectReceiver();
                    receiverOfMessage= StringUtils.remove(receiverOfMessage,"(online)").trim();
                    String commandMessage = "chattingTo:"+ receiverOfMessage;
                    sendMessage.sendMes(commandMessage,contentMessage);
                    textAreaMessage.setText("");
    //                panelCorrespondence.getScrollCorrespondence().add(new JLabel(txt));
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

        panelMessage= new JPanel(new BorderLayout());
            fill_receiverBook();
            panelMessage.add(scrollMessage, BorderLayout.SOUTH);


        add(panelMessage, BorderLayout.WEST);
        add(buttonSend,BorderLayout.EAST);

        setSize(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize());
    }



    protected String getMessage (){
        return textAreaMessage.getText();
    }

    protected String getSelectReceiver () {return  receiver.getItemAt(receiver.getSelectedIndex()); }

    void fill_receiverBook() {
        receiver=new JComboBox<>();
        receiver.setFont(MyFonts.FONT_LIST_CORRESPONDENCE.getFont());
        receiver.addItem("to all");
//        receiver.addItem("to all online");

        log.info("!!!{} -set  reference Book Client " ,chatFrame.getReferenceBook());
        chatFrame.getReferenceBook().forEach((user,online)->{
            String str ;
            if (online){
                str=user +" (online)";
            }else {
                str=user;
            }

            receiver.addItem(str);
        });
        panelMessage.add(receiver, BorderLayout.NORTH);
    }

}
