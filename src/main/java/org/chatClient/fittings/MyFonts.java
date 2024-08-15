package org.chatClient.fittings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.awt.*;

@Getter
@AllArgsConstructor
public enum MyFonts {

    FONT_BUTTON (new Font("Franklin Gothic Medium", Font.PLAIN, MyFontSizes.FRONT_SIZE_BUTTON.getFontSize())),
    FONT_AREA (new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_MESSAGE.getFontSize())),
    FONT_PASSWORD (new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_PASSWORD.getFontSize())),
    FONT_LABEL_REACT (new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_LABEL_REACT.getFontSize())),
    FONT_LABEL (new Font("Arial", Font.BOLD, MyFontSizes.FRONT_SIZE_LABEL.getFontSize())),


    FONT_LABEL_SENDER_CORRESPONDENCE (new Font("Franklin Gothic Medium", Font.BOLD, 15));


    private Font font;

}
