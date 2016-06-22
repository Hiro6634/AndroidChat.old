package edu.galileo.android.androidchat.login;

/**
 * Created by Hiro on 21/06/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenterImpl( LoginView loginView ){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }
    @Override
    public void onDestroy() {
        loginView = null;
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
}
