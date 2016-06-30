package edu.galileo.android.androidchat.contactlist;

/**
 * Created by Hiro on 30/06/2016.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
