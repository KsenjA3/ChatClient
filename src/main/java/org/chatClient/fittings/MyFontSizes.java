package org.chatClient.fittings;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MyFontSizes {
    FRONT_SIZE_LABEL_CORRESPONDENCE(14),
    FRONT_SIZE_BUTTON_CORRESPONDENCE(15),
    FRONT_SIZE_LIST_CORRESPONDENCE(14),

    FRONT_SIZE_LABEL_SENDER_CORRESPONDENCE(15),
    FRONT_SIZE_TEXT_CORRESPONDENCE(15),

    FRONT_SIZE_MESSAGE(15),
    FRONT_SIZE_BUTTON(16),
    FRONT_SIZE_LABEL(16),
    FRONT_SIZE_LABEL_REACT(18),
    FRONT_SIZE_PASSWORD(18);

    private int fontSize;

}