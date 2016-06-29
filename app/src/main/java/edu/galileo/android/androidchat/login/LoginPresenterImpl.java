package edu.galileo.android.androidchat.login;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.GreenRobotEventBus;
import edu.galileo.android.androidchat.login.events.LoginEvent;
import edu.galileo.android.androidchat.login.ui.LoginView;

/**
 * Created by Hiro on 21/06/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenterImpl( LoginView loginView ){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }


    @Override
    public void checkForAuthentication() {
        if( loginView != null ){
            loginView.disableInputs();
            loginView.showProgressBar();
        }

        loginInteractor.checSession();
    }

    @Override
    public void validationLogin(String email, String password) {
        if( loginView != null ){
            loginView.disableInputs();
            loginView.showProgressBar();
        }

        loginInteractor.doSignIn( email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if( loginView != null ){
            loginView.disableInputs();
            loginView.showProgressBar();
        }

        loginInteractor.doSignUp(email, password);
    }

    private void onSigInSuccess(){
        if( loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSigUpSuccess(){
        if( loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onSigInError(String error){
        if( loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSigUpError(String error){
        if( loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch( event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSigInSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSigUpSuccess();
                break;
            case LoginEvent.onSignInError:
                onSigInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpError:
                onSigUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if( loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");

    }
}
