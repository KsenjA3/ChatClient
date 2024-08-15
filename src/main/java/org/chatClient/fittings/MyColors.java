package org.chatClient.fittings;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@Getter
@AllArgsConstructor
public enum MyColors {

    COLOR_AREA_MESSAGE_BACKGROUND (new Color(236, 231, 237)),
    COLOR_PANEL_MESSAGE_FOREGROUND (Color.GRAY),

    COLOR_AREA_CORRESPONDENCE_BACKGROUND (new Color(231, 223, 232)),
    COLOR_LABEL_SENDER_CORRESPONDENCE_BACKGROUND (Color.GRAY);

//    COLOR_PANE (new Color(231, 223, 232)),
//
//    COLOR_INPUT (new Color(231, 223, 232)),
//    COLOR_RESULT(new Color(236, 231, 237)),
//    COLOR_LOG (Color.GRAY),
//
//    COLOR_BUTTON (new Color(236, 231, 237)),
//    COLOR_SIGN(new Color(213, 199, 214)),
//    COLOR_BUTTON_MEMORY (new Color(201, 184, 203));

    private Color color;

}

