package edu.galileo.android.androidchat.login;

import android.util.Log;

import edu.galileo.android.androidchat.domain.FirebaseHelper;

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
        Log.e("LoginRepositoryImpl", "signUp");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl", "signIn");

    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl", "checkSession");

    }
}
