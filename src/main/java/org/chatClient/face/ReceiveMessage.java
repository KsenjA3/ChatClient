package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

@Log4j2
public class ReceiveMessage implements Runnable{
    private Socket socket;
    private String userName;
    private JFrame frame;
    private PrintWriter outNet;
    private boolean done;
    private ChatFrame chatFrame;

    ReceiveMessage( ChatFrame chatFrame){
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        socket=chatFrame.getSocketClient();
        userName=chatFrame.getNameUser();

    }

    public void run() {

        System.out.println("ReceiveMessage.run"  +socket);
        String line, command, user = "", message = "", receiver = "";
        try {
            InputStream inStream = socket.getInputStream();
            BufferedReader brNet = new BufferedReader(new InputStreamReader(inStream));

            done = true;
            while (done) {

                /** Form full message from server:
                 * command, user, message.
                 * При неудаче начинает сначала.
                 */

                while (brNet.ready()) {
                    line = brNet.readLine();
                    if (StringUtils.startsWith(line, "command:")) {
                        command = StringUtils.removeStart(line, "command:");

                        if (brNet.ready()) {
                            line = brNet.readLine();
                            if (StringUtils.startsWith(line, "user:")) {
                                user = StringUtils.removeStart(line, "user:");

                                if (brNet.ready()) {
                                    line = brNet.readLine();
                                    if (StringUtils.startsWith(line, "message:")) {
                                        message = StringUtils.removeStart(line, "message:");
                                    }
                                }
                            }
                        }
                    } else continue;
                    System.out.println("111-"+command);
                    System.out.println("222-"+user);
                    System.out.println("333-"+message);
                    react_for_message ( command,  user,  message);

                }

            }


        } catch (IOException e) {
            log.error(e);
            e.printStackTrace();
        }
    }


    void react_for_message (String command, String user, String message) {

        if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(message)) {
            switch (command){

                case "chatting"->{

                }
                case "referenceBook"->{
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        TreeMap<String, Boolean> mapClient=  mapper.readValue(message, new TypeReference<TreeMap<String, Boolean>>() {});
                        chatFrame.getPanelMessage().setReferenceBook(mapClient);
                        log.info("{} -set  reference Book Client " ,mapClient);

                        //метод прописать справочник в PanelMessage
                        chatFrame.getPanelMessage().getPanelMessage().remove( chatFrame.getPanelMessage().getReceiver());
                        chatFrame.getPanelMessage().fill_receiverBook();
                        chatFrame.getPanelMessage().revalidate();
                        chatFrame.getPanelMessage().repaint();
                        chatFrame.frame.pack();

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                case "exit"->{
                    done=false;

                }

            }
        }
    }


//    HashMap<String, Boolean>  referenceBook_from_JSON (String bookJSON){
//        ObjectMapper mapper = new ObjectMapper();
//        // Converting JSON  to a map
//        try {
//            referenceBook=mapper.readValue(bookJSON,HashMap.class);
//
//            log.info("{} - reference Book " ,referenceBook);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//
//        return referenceBook;
//    }
}
