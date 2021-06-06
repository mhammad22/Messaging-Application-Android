package com.example.messagingapp3;

public class ChatMessage {


    public void setType(String type) {
        Type = type;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    private String message;
    private String Name;
    private String time;
    private  String Type;
    private String ReceiverName;

    public ChatMessage() {
        message = null;
        Name = null;
        time = null;
    }

    public ChatMessage(String message, String name, String time, String type, String receiverName) {
        this.message = message;
        Name = name;
        this.time = time;
        Type = type;
        ReceiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return Name;
    }

    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return Type;
    }

    public String getReceiverName() {
        return ReceiverName;
    }
}
