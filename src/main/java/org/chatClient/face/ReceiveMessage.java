package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.chatClient.fittings.MyColors;

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
    private ChatFrame chatFrame;
    private SendMessage sendMessage;
    private boolean done;

    ReceiveMessage( ChatFrame chatFrame){
        this.chatFrame=chatFrame;
        frame=chatFrame.frame;
        socket=chatFrame.getSocketClient();
        userName=chatFrame.getNameUser();
        sendMessage= new SendMessage(chatFrame);
    }

    public void run() {
        log.info("ReceiveMessage.run - {} " ,socket);
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

                    System.out.println();
                    System.out.println("---Receive---:");
                    System.out.println("1command- "+command);
                    System.out.println("2user- "+user);
                    System.out.println("3message- "+message);

                    react_for_message ( command,  user,  message);
                    sendMessage.sendMes(command,  message);
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
                case "newMessages"->{
                    chatFrame.getPanelCorrespondence().getButtonNewMessage().
                            setBackground(MyColors.COLOR_NEW_MESSAGES.getColor());
                    frame.pack();
                }
                case "correspondence"->{
                    try {
                    ObjectMapper mapper = new ObjectMapper();
                    TreeMap<String, String> mapCorrespondence=  mapper.readValue(message, new TypeReference<TreeMap<String, String>>() {});
                        mapCorrespondence.forEach((collocutor,mess)->{
                            String infoCollocutor = turnCollocutorForm(collocutor);
                            chatFrame.getPanelCorrespondence().print_one_correspondence(infoCollocutor,mess);
                        });
                    chatFrame.frame.pack();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                case "referenceBook"->{
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        TreeMap<String, Boolean> mapClient=  mapper.readValue(message, new TypeReference<TreeMap<String, Boolean>>() {});
                        chatFrame.setReferenceBook(mapClient);

                        //прописать справочник в PanelMessage
                        chatFrame.getPanelMessage().getPanelReceiver()
                                .remove( chatFrame.getPanelMessage().getReceiverComboBox());
                        chatFrame.getPanelMessage().fill_receiverBook();
                        chatFrame.getPanelMessage().getPanelReceiver().revalidate();

                        //прописать справочник в PanelCorrespondence
                        chatFrame.getPanelCorrespondence(). getPaneRequestCorrespondence()
                                .remove(chatFrame.getPanelCorrespondence().getPersonComboBox());
                        chatFrame.getPanelCorrespondence().fill_personComboBox();
                        chatFrame.getPanelCorrespondence().revalidate();

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

     protected String turnCollocutorForm (String collocutor){
         int indexEndTime = collocutor.indexOf('-');
         int indexEndCollocutor= collocutor.indexOf(':');
         String infoCollocutor ="<html>"+collocutor.substring(indexEndTime+1, indexEndCollocutor+1)
                 +" "+ collocutor.substring(6,indexEndTime);
         if (collocutor.contains("<br>"))
             infoCollocutor=infoCollocutor+collocutor.substring(indexEndCollocutor+1, collocutor.length()-7);
         infoCollocutor=infoCollocutor+"</html>";
         infoCollocutor=StringUtils.replaceOnce(infoCollocutor,"<html>TO","<html>&ensp;TO");
         infoCollocutor=StringUtils.replaceOnce(infoCollocutor,"<html>FROM","<html>&ensp;FROM");
         infoCollocutor=StringUtils.replaceOnce(infoCollocutor,"<br>(familiarized","<br>&nbsp;(familiarized");
         return infoCollocutor;
     }

}
