package edu.galileo.android.androidchat.addcontact;

import edu.galileo.android.androidchat.addcontact.event.AddContactEvent;
import edu.galileo.android.androidchat.addcontact.ui.AddContactFragment;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;

/**
 * Created by Hiro on 03/07/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}












