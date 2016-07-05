package edu.galileo.android.androidchat.entities;
import com.google.firebase.database.Exclude;
/**
 * Created by Hiro on 04/07/2016.
 */
public class ChatMessage {
    private String msg;
    private String sender;
    @Exclude
    private boolean sendByMe;

    public ChatMessage(){}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSendByMe() {
        return sendByMe;
    }

    public void setSendByMe(boolean sendByMe) {
        this.sendByMe = sendByMe;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if( obj instanceof ChatMessage){
            ChatMessage msg = (ChatMessage) obj;
            equal = this.sender.equals(msg.getSender()) && this.msg.equals(msg.getMsg()) && this.sendByMe == msg.isSendByMe();
        }
        return equal;
    }
}
