package edu.galileo.android.androidchat.login.ui;

/**
 * Created by Hiro on 21/06/2016.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void hideProgressBar();
    void showProgressBar();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
