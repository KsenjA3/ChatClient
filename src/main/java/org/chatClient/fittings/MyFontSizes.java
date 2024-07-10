package org.chatClient.fittings;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MyFontSizes {
    FRONT_SIZE_MESSAGE(15),
    FRONT_SIZE_BUTTON(16),
    FRONT_SIZE_LABEL(16),
    FRONT_SIZE_LABEL_REACT(18),
    FRONT_SIZE_PASSWORD(18);

//    FRONT_SIZE_TEXT_LOG (18),
//    FRONT_SIZE_TEXT_INPUT (25),
//    FRONT_SIZE_TEXT_RESULT (20);

    private int fontSize;

}