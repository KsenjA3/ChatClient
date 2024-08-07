package org.chatClient.face;

import lombok.Getter;
import org.chatClient.fittings.MyColors;
import org.chatClient.fittings.MyFonts;
import org.chatClient.fittings.MySizePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


/** In this program organize GUI for chat.
 *  JPanel for receiving  messages.
 * */
class PanelCorrespondence extends JPanel {
    @Getter
    JScrollPane scrollCorrespondence;

    private final JLabel labelCorrespondence;
    private final StringBuffer allTextCorrespondence;
    JPanel panel;

    PanelCorrespondence() {
        allTextCorrespondence= new StringBuffer();
        labelCorrespondence= new JLabel();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        scrollCorrespondence = new JScrollPane (panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCorrespondence.setBorder(null);

        for (int i =1; i<20; i++){
            var lab = new JLabel("text from somebody text from somebody text from somebody" +i+ "\n");
            lab.setFont(MyFonts.FONT_LABEL.getFont());
            lab.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
            lab.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lab);
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

        labelCorrespondence.setText(allTextCorrespondence.toString());
    }


}
