package edu.galileo.android.androidchat.addcontact.ui;

/**
 * Created by Hiro on 02/07/2016.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
