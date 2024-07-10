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
//    private JTextPane paneMessage;

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

        textAreaMessage= new JTextArea(8, 21);
        textAreaMessage.setBackground(MyColors.COLOR_AREA_MESSAGE_BACKGROUND.getColor());
        textAreaMessage.setFont(MyFonts.FONT_AREA.getFont());
        textAreaMessage.setLineWrap(true);
        textAreaMessage.setWrapStyleWord(true);
//        textAreaMessage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//        textAreaMessage.setSize(MySizePanel.WIDTH_SIZE_AREA_MESSAGE.getSize(),MySizePanel.HEIGHT_SIZE_AREA_MESSAGE.getSize());


//        paneMessage = new JTextPane();
//        var textLogAttributes = new SimpleAttributeSet();
//        StyleConstants.setAlignment(textLogAttributes, StyleConstants.ALIGN_LEFT);
//        StyleConstants.setFontFamily(textLogAttributes, MyFontNames.FRONT_NAME_MESSAGE.getFontName());
//        StyleConstants.setFontSize(textLogAttributes, MyFontSizes.FRONT_SIZE_MESSAGE.getFontSize() );
//        StyleConstants.setForeground(textLogAttributes, MyColors.COLOR_PANEL_MESSAGE_FOREGROUND.getColor());
//        paneMessage.setParagraphAttributes(textLogAttributes, true);
//        paneMessage.setSize(MySizePanel.WIDTH_SIZE_AREA_MESSAGE.getSize(),MySizePanel.HEIGHT_SIZE_AREA_MESSAGE.getSize());

        scrollMessage = new JScrollPane (textAreaMessage,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        setSize(MySizePanel.WIDTH_SIZE_PANEL_MESSAGE.getSize(),MySizePanel.HEIGHT_SIZE_PANEL_MESSAGE.getSize());
        add(scrollMessage, BorderLayout.WEST);
        add(buttonSend,BorderLayout.EAST);
    }

    String getMessage (){
        return textAreaMessage.getText();
    }

}
