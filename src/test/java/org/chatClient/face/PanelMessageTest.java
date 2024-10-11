package org.chatClient.face;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PanelMessageTest {

    @Mock
    ChatFrame chatFrame;

    private PanelMessage panelMessage;

    @BeforeEach
    void setUp() {
        panelMessage = new PanelMessage(chatFrame);
    }


    @Test
    void getPanelMessage() {
        panelMessage.getTextAreaMessage().setText("qqq");
        assertEquals("qqq", panelMessage.getMessage());

        panelMessage.getTextAreaMessage().setText("\nqqq\nwwww\neeeee");
        assertEquals("\nqqq\nwwww\neeeee", panelMessage.getMessage());

        panelMessage.getTextAreaMessage().setText("""                
                qqq
                wwww""");
        assertEquals("qqq\nwwww", panelMessage.getMessage());


    }




}