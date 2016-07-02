package edu.galileo.android.androidchat.login;

import edu.galileo.android.androidchat.login.events.LoginEvent;

/**
 * Created by Hiro on 21/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validationLogin( String email, String password);
    void registerNewUser( String email, String password);
    void onEventMainThread(LoginEvent event);
}
