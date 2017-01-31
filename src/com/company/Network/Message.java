package com.company.Network;

import java.io.Serializable;

public class Message implements Serializable {
    String header;
    Object object;

    public Message(String header, Object object){
        this.header = header;
        this.object =  object;
    }
    public String getHeader() { return header; }
    public Object getObject() { return object; }
}
