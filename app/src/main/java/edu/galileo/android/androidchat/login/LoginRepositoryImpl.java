package edu.galileo.android.androidchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

//import com.firebase.client.AuthData;
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.firebase.client.snapshot.BooleanNode;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.User;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.GreenRobotEventBus;
import edu.galileo.android.androidchat.login.events.LoginEvent;

/**
 * Created by Hiro on 21/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;
//    private Firebase dataReference;
//    private Firebase myUserReference;
    private DatabaseReference dataReference;
    private DatabaseReference myUserReference;

    public LoginRepositoryImpl (){

        this.helper = FirebaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }

//    @Override
//    public void signUp(final String email, final String password) {
//        dataReference.createUser( email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> stringObjectMap) {
//                signIn(email, password);
//                postEvent(LoginEvent.onSignUpSuccess);
//           }
//
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
//            }
//        });
//    }

    @Override
    public void signUp(final String email, final String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        postEvent(LoginEvent.onSignUpSuccess);
                        signIn(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignUpError, e.getMessage());
                    }
                });
    }


//    @Override
//    public void signIn(String email, String password) {
//        dataReference.authWithPassword( email, password, new Firebase.AuthResultHandler() {
//            @Override
//           public void onAuthenticated(AuthData authData) {
//                myUserReference = helper.getMyUserReference();
//                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        User currentUser = dataSnapshot.getValue(User.class);
//                        if( currentUser == null){
//                            String email = helper.getAuthUserEmail();
//                            if( email != null){
//                                currentUser = new User();
//                                myUserReference.setValue(currentUser);
//                            }
//                        }
//                        helper.changeUserConnectionStatus(User.ONLINE);
//                        postEvent( LoginEvent.onSignInSuccess);
//                    }
//                });
//            }
//        });
//    }

    @Override
    public void signIn(String email, String password) {
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            initSignInOld();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postEvent(LoginEvent.onSignInError, e.getMessage());
                        }
                    });
        } catch(Exception e){
            postEvent(LoginEvent.onSignInError, e.getMessage());
        }
    }

    @Override
    public void checkSession() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            initSignInOld();
        }else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    @Override
    public void checkAlreadyAuthenticated(){
        if( FirebaseAuth.getInstance().getCurrentUser() != null){
            myUserReference = helper.getMyUserReference();
            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    initSignIn(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    postEvent(LoginEvent.onSignInError, databaseError.getMessage());
                }
            });
        }
        else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    private void initSignIn(DataSnapshot dataSnapshot) {
        User currentUser = dataSnapshot.getValue(User.class);

        if(currentUser == null){
            registerNewUser();
        }
        helper.changeUserConnectionStatus(User.ONLINE);
        postEvent(LoginEvent.onSignInSuccess);
    }

    private void initSignInOld(){
        myUserReference = FirebaseDatabase.getInstance().getReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                if( currentUser == null ){
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(LoginEvent.onSignInError, databaseError.getMessage());
            }
        });
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if(email != null){
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
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
