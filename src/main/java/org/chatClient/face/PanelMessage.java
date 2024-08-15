package org.chatClient.face;

import org.chatClient.fittings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** In this program organize GUI for chat.
 *  JPanel for sending  messages.
 * */
class PanelMessage extends JPanel {
    String txt;
    private JButton buttonSend;

    private JTextArea textAreaMessage;
    private JPanel panelMessage;
    private JComboBox< String> receiver;

    private  JScrollPane scrollMessage;

    PanelMessage(PanelCorrespondence panel, JFrame frame){

        buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt= getMessage();
               panel.getScrollCorrespondence().add(new JLabel(txt));
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

        receiver=new JComboBox<>();
        receiver.addItem("to all");
        receiver.addItem("to all online");

//        for (int i = 0; i < ; i++) {
//            String str = ;
//            receiver.addItem(str);
//        }

        panelMessage= new JPanel(new BorderLayout());
        panelMessage.add(receiver, BorderLayout.NORTH);
        panelMessage.add(scrollMessage, BorderLayout.SOUTH);


        add(panelMessage, BorderLayout.WEST);
        add(buttonSend,BorderLayout.EAST);

        setSize(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize());
    }

    String getMessage (){
        return textAreaMessage.getText();
    }

}
