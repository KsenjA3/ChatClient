package org.chatClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.util.HashMap;

public class Example {

    static HashMap<String, Boolean> referenceBook;

    public static void main(String[] args) {
        String bookJSON= """
                 {
            "111" : false,
                "222" : false,
                "333" : false,
                "www" : true
        }
        """;
        referenceBook_from_JSON ( bookJSON);
    }

    static HashMap<String, Boolean> referenceBook_from_JSON (String bookJSON){
        ObjectMapper mapper = new ObjectMapper();
        // Converting JSON  to a map
        try {
            referenceBook=mapper.readValue(bookJSON,HashMap.class);
            System.out.println(referenceBook);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return referenceBook;
    }
}
