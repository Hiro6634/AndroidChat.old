package edu.galileo.android.androidchat.contactlist;

/**
 * Created by Hiro on 30/06/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);


}
