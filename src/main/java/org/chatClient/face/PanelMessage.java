package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
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
    @Getter
    @Setter
    private TreeMap<String, Boolean> referenceBook;
    @Getter
    private JComboBox< String> receiver;
    @Getter
    private JPanel panelMessage;

    private JFrame frame;
    private PanelCorrespondence panelCorrespondence;
    private String txt;
    private JButton buttonSend;
    private JTextArea textAreaMessage;

    private  JScrollPane scrollMessage;

    PanelMessage( ChatFrame chatFrame){
       frame=chatFrame.frame;
        panelCorrespondence=chatFrame.getPanelCorrespondence();
       referenceBook= new TreeMap<>();


        buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt= getMessage();
                panelCorrespondence.getScrollCorrespondence().add(new JLabel(txt));
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
        panelMessage.add(scrollMessage, BorderLayout.SOUTH);

        referenceBook= new TreeMap<>();
        fill_receiverBook();

        add(panelMessage, BorderLayout.WEST);
        add(buttonSend,BorderLayout.EAST);

        setSize(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize());
    }

    String getMessage (){
        return textAreaMessage.getText();
    }

    void fill_receiverBook() {
        receiver=new JComboBox<>();
        receiver.addItem("to all");
        receiver.addItem("to all online");
        System.out.println("!!!"+referenceBook);
        referenceBook.forEach((user,online)->{
            String str ;
            if (online){
                str=user +" (online)";
            }else {
                str=user;
            }

            receiver.addItem(str);
        });

        panelMessage.add(receiver, BorderLayout.NORTH);
//                        TreeSet<String> referenceBookSet = new TreeSet<>();
//                        mapClient.forEach((sub,online)->{
//                            if (online){
//                                String subscriber=sub+"(online)";
//                                referenceBookSet.add(subscriber);
//                            }else {
//                                referenceBookSet.add(sub);
//                            }
//                        });
    }

}
