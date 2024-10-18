package org.chatClient.face;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReceiveMessageTest {
    @Mock
    ChatFrame chatFrame;

    ReceiveMessage receiveMessage;

    @BeforeEach
    void setUp() {
        receiveMessage= new ReceiveMessage(chatFrame);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void turnCollocutorForm() {
        String collocutor = "<html>on 16.10.2024 at 15.03.36-FROM 333:<br>(familiarized on 18.10.2024 at 00.19.03)</html>";
        String expect = "<html>&ensp;FROM 333: on 16.10.2024 at 15.03.36<br>&nbsp;(familiarized on 18.10.2024 at 00.19.03)</html>";
        assertEquals(expect,receiveMessage.turnCollocutorForm(collocutor));
    }
    @Test
    void turnCollocutorFormFamiliarized() {
        String collocutor = "<html>on 16.10.2024 at 15.03.36-FROM 333:</html>";
        String expect = "<html>&ensp;FROM 333: on 16.10.2024 at 15.03.36</html>";
        assertEquals(expect,receiveMessage.turnCollocutorForm(collocutor));

    }
}