package edu.galileo.android.androidchat.chat;

/**
 * Created by Hiro on 04/07/2016.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);

}
