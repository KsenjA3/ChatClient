package org.chatClient.face;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeSet;

@Log4j2
public class ReceiveMessage {
    private Socket socket;
    private String userName;

    private PrintWriter outNet;
    private boolean done;

    ReceiveMessage(Socket s, String userName){
        socket=s;
        this.userName=userName;
    }

    public void run() {
        String line, command, user = "", message = "", receiver = "";
        try {
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();
            BufferedReader brNet = new BufferedReader(new InputStreamReader(inStream));
            outNet = new PrintWriter(outStream, true);

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
                                    if (StringUtils.startsWith(line, "message")) {
                                        message = StringUtils.removeStart(line, "message");
                                    }
                                }
                            }
                        }
                    } else continue;

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
                        HashMap<String, Boolean> mapClient= mapper.readValue(message, new TypeReference<HashMap<String, Boolean>>() {});
                        TreeSet<String> referenceBookSet = new TreeSet<>();

                        mapClient.forEach((sub,online)->{
                            if (online){
                                String subscriber=sub+"(online)";
                                referenceBookSet.add(subscriber);
                            }else {
                                referenceBookSet.add(sub);
                            }
                        });

                        log.info("{} -set  reference Book Client " ,referenceBookSet);
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

}
