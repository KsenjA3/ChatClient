package org.chatClient.face;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StartFrameTest {

    StartFrame frame;
    Robot bot;

    @Mock
    Socket socketClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @BeforeAll
    void init() throws IOException {
        MockitoAnnotations.openMocks(this);

        frame= new StartFrame();

        Mockito.doNothing().when(socketClient).close();
    }

    @Test
    void userName_input(){
        frame.getUserName().setText("12345678901234567890");
        frame.check_client_input ();
        assertEquals("<html>   User name could not be longer<br>then 15 signs.</html>", frame.getReactionLabel().getText());

        frame.getUserName().setText("");
        frame.check_client_input ();
        assertEquals("Enter the user userName.", frame.getReactionLabel().getText());
    }


    @Test
    void password_input(){
        frame.getUserName().setText("12345");
        frame.getPassword().setText("12345678901234567890");
        frame.check_client_input ();
        assertEquals("<html>Password could not be longer<br>then 15 signs.</html>", frame.getReactionLabel().getText());

        frame.getPassword().setText("");
        frame.check_client_input ();
        assertEquals("Enter the password.", frame.getReactionLabel().getText());

    }


    @ParameterizedTest
    @CsvSource( value =  {
            " any, upset, Don't have response from server.",
            "registration,OK,The registration has been a success.",
            "registration,BUSY,User name is occupied.",
            "in account,NoUser,User name don't have a registration.",
            "in account,NoPassword,Wrong password.",
            "in account,BUSY,User name is used at this time."

    })
    void responseFromServer (String command, String response, String expectedResult) throws IOException {
        frame.reaction_for_response_from_server(socketClient, command,response);
        assertEquals(expectedResult, frame.getReactionLabel().getText());

    }

    @Test
    void responseFromServer_IN () throws IOException {
        frame.reaction_for_response_from_server(socketClient, "in account","OK");
        assertEquals(false, frame.isVisible());

    }

}