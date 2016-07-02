package edu.galileo.android.androidchat.login;

/**
 * Created by Hiro on 21/06/2016.
 */
public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp( String email, String password);
    void doSignIn( String email, String password);

}
