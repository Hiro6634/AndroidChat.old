package edu.galileo.android.androidchat.contactlist;

import edu.galileo.android.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by Hiro on 30/06/2016.
 */
public interface ContactListPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);

    void onEventMainThread(ContactListEvent event);

}



