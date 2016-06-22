package edu.galileo.android.androidchat.login;

import android.util.Log;

import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.GreenRobotEventBus;
import edu.galileo.android.androidchat.login.events.LoginEvent;

/**
 * Created by Hiro on 21/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;

    public LoginRepositoryImpl (){
        this.helper = FirebaseHelper.getInstance();
    }
    @Override
    public void signUp(String email, String password) {
        postEvent( LoginEvent.onSignUpSuccess);
    }

    @Override
    public void signIn(String email, String password) {
        postEvent( LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSession() {
        postEvent( LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage( errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type ){
        postEvent(type, null);
    }
}
