package edu.galileo.android.androidchat.chat;

import edu.galileo.android.androidchat.chat.events.ChatEvent;

/**
 * Created by Hiro on 04/07/2016.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
