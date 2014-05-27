/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafiddle.web.services.websockets;

/**
 *
 * @author Александр
 */
public class Message {
    private String type;
    private Object data;
    
    public String toString(){
        return "type: "+type+"  data: "+data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
