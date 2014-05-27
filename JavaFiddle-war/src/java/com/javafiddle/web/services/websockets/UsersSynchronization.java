/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafiddle.web.services.websockets;

import com.google.gson.Gson;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Александр
 */
@ServerEndpoint("/endpoint")
public class UsersSynchronization {
    
    private Map<Session, Long> userSessions = new HashMap<>();

    @OnOpen
     public void onOpen(final Session session, EndpointConfig config) {
        
         System.out.println("Session created: "+session);
         userSessions.put(session, null);
     }
    
    @OnMessage
    public String onMessage(Session session, String message) {        
        Gson gson = new Gson();
        Message mes = gson.fromJson(message, Message.class);
        if(mes.getType().equals("init")){            
            
            NumberFormat format = NumberFormat.getNumberInstance();
            format.setMaximumFractionDigits(0);
            
            Long userId = new Long(format.format((Double) mes.getData()));
            System.out.println("Initialising of userId: "+userId);
            userSessions.put(session, userId);
        }
        
        return null;
    }
    
}
