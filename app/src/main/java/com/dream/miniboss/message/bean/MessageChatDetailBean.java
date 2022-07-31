package com.dream.miniboss.message.bean;

public class MessageChatDetailBean {
    public static final int TYPE_SEND = 1;
    public static final int TYPE_RECEIVED = 0;


    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    private String message;
    private int type;

    public MessageChatDetailBean(String message, int type) {
        this.message = message;
        this.type = type;
    }
}
