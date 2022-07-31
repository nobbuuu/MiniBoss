package com.dream.miniboss.message.bean;

public class MessageChatDetailBean {
    String sendMessage;
    String receiveMessage;

    public MessageChatDetailBean() {
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }
}
