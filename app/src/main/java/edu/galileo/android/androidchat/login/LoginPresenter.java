package edu.galileo.android.androidchat.login;

/**
 * Created by Hiro on 21/06/2016.
 */
public interface LoginPresenter {
    void onDestroy();
    void checkForAuthentication();
    void validationLogin( String email, String password);
    void registerNewUser( String email, String password);
}
