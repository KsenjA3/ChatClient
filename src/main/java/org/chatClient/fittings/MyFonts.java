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
    FONT_LABEL (new Font("Arial", Font.BOLD, MyFontSizes.FRONT_SIZE_LABEL.getFontSize()));

//    FONT_CHECKBOX (new Font("Arial", Font.BOLD, 13)),
//    FONT_BUTTON (new Font("Franklin Gothic Medium", Font.PLAIN, 30)),
//    FONT_BUTTON_MIDDLE (new Font("Franklin Gothic Medium", Font.PLAIN, 27)),
//    FONT_BUTTON_LOW (new Font("Franklin Gothic Medium", Font.PLAIN, 20)),
//    FONT_BUTTON_BOTTOM (new Font("Franklin Gothic Medium", Font.PLAIN, 18)),
//    FONT_BUTTON_MEMORY (new Font("Cambria", Font.PLAIN, 30)),
//
//    FONT_TEXT_INPUT(new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_TEXT_INPUT.get())),
//    FONT_TEXT_RESULT(new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_TEXT_RESULT.get())),
//    FONT_TEXT_LOG(new Font("Arial", Font.PLAIN, MyFontSizes.FRONT_SIZE_TEXT_LOG.get())),
//
//    FONT_MENU (new Font("Arial", Font.PLAIN, 15)),
//    FONT_MENU_ITEM (new Font("Arial", Font.PLAIN, 13));


    private Font font;

}
