package edu.galileo.android.androidchat.chat.ui;

import edu.galileo.android.androidchat.entities.ChatMessage;

/**
 * Created by Hiro on 04/07/2016.
 */
public interface ChatView {
    void nMessageReceived(ChatMessage msg);
}
