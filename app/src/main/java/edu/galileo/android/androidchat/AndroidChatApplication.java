package edu.galileo.android.androidchat;

import android.app.Application;

//import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Hiro on 20/06/2016.
 */
public class AndroidChatApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
//        Firebase.setAndroidContext(this);
//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
