package org.chatClient.face;

import lombok.Getter;
import org.chatClient.fittings.MyColors;
import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;

import javax.swing.*;
import java.awt.*;


/** In this program organize GUI for chat.
 *  JPanel for receiving  messages.
 * */
class  PanelCorrespondence extends JPanel {
    @Getter
    JScrollPane scrollCorrespondence;
    JFrame frame;
    private  JLabel labelSender;
    private  StringBuffer allTextCorrespondence;
    JPanel panel;
    JTextArea txtCorrespondence;

    PanelCorrespondence(ChatFrame chatFrame) {
//        allTextCorrespondence= new StringBuffer();

        frame=chatFrame.frame;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        scrollCorrespondence = new JScrollPane (panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCorrespondence.setBorder(null);
        scrollCorrespondence.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollCorrespondence.setSize(MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize());

        String sender = " somebody";
        String message = "text text text text text text text text text text text text text text" +
                "text text text text text text text text text text text text text text text text  --  ";

        for (int i =1; i<20; i++){
            JPanel panelLabel = new JPanel(new BorderLayout());
            panelLabel.setSize(MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
                    MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize());

            labelSender= new JLabel();
            labelSender.setFont(MyFonts.FONT_LABEL_SENDER_CORRESPONDENCE.getFont());
            labelSender.setBackground(MyColors.COLOR_LABEL_SENDER_CORRESPONDENCE_BACKGROUND.getColor());
            labelSender.setText(sender);

            panelLabel.add(labelSender, BorderLayout.WEST);

            txtCorrespondence = new JTextArea();
            txtCorrespondence.setBackground(MyColors.COLOR_AREA_MESSAGE_BACKGROUND.getColor());
            txtCorrespondence.setFont(MyFonts.FONT_LABEL.getFont());
            txtCorrespondence.setLineWrap(true);
            txtCorrespondence.setWrapStyleWord(true);
            txtCorrespondence.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
            txtCorrespondence.setText(message+ i);

            panel.add(panelLabel);
            panel.add(txtCorrespondence);
            panel.add(Box.createRigidArea(new Dimension(2, 8)));
        }


        scrollCorrespondence.setPreferredSize( new Dimension(
                MySizePanel.WIDTH_SIZE_PANEL_CORRESPONDENCE.getSize(),
                MySizePanel.HEIGHT_SIZE_PANEL_CORRESPONDENCE.getSize()));
        add(scrollCorrespondence);
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


}
