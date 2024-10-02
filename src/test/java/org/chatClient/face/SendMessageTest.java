package org.chatClient.face;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SendMessageTest {
    @Mock
    ChatFrame chatFrame;

    @Mock
    Socket socket;

    private SendMessage sendMessage;

    @BeforeEach
    void setUp() {
        sendMessage =new SendMessage(chatFrame);
    }



    @Test
    void sendMes() {
    }

    @Test
    void responseSendMes() {
    }
}