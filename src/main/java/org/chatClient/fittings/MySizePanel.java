package org.chatClient.fittings;

import lombok.Getter;

@Getter
public enum MySizePanel {
    HEIGHT_SIZE_WINDOW (600),
    WIDTH_SIZE_WINDOW (400),

    HEIGHT_SIZE_WINDOW_INIT (300),
    WIDTH_SIZE_WINDOW_INIT (350),

    HEIGHT_SIZE_PANEL_CORRESPONDENCE (400),
    WIDTH_SIZE_PANEL_CORRESPONDENCE (400),

    HEIGHT_SIZE_PANEL_MESSAGE (2300),
    WIDTH_SIZE_PANEL_MESSAGE(400),

    HEIGHT_SIZE_AREA_MESSAGE (200),
    WIDTH_SIZE_AREA_MESSAGE(300);


    private int size;

    MySizePanel (int size){
        this.size= size;
    }
}
