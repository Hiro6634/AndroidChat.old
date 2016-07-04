package edu.galileo.android.androidchat.addcontact.event;

/**
 * Created by Hiro on 02/07/2016.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
