package edu.galileo.android.androidchat.addcontact;

import edu.galileo.android.androidchat.addcontact.event.AddContactEvent;

/**
 * Created by Hiro on 02/07/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
