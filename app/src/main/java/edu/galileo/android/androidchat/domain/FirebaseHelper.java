package edu.galileo.android.androidchat.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.galileo.android.androidchat.entities.User;

/**
 * Created by Hiro on 21/06/2016.
 */
public class FirebaseHelper {
    //private Firebase dataReference;
    private DatabaseReference dataReference;
    private final static String SEPARATOR = "___";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    private final static String CONTACTS_PATH = "contacts";
    private final static String FIREBASE_URL = "https://androidchat-d72f0.firebaseio.com";

    private static class SingletoneHolder{
        private final static FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletoneHolder.INSTANCE;
    }


//    public FirebaseHelper(){
//        this.dataReference = new Firebase(FIREBASE_URL);
//    }

    public FirebaseHelper() {
        dataReference = FirebaseDatabase.getInstance().getReference();
    }
//    public Firebase getDataReference(){
//        return dataReference;
//    }

    public DatabaseReference getDataReference(){
        return dataReference;
    }

//    public String getAuthUserEmail(){
//        AuthData authData = dataReference.getAuth();
//        String email = null;
//        if( authData != null){
//            Map<String, Object> providerData = authData.getProviderData();
//            email = providerData.get("email").toString();
//        }
//        return email;
//    }

    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if(user != null){
            email = user.getEmail();
        }
        return email;
    }

//    public Firebase getUserReference( String email ){
//        Firebase userReference = null;
//        if( email != null){
//            String emailKey = email.replace(".", "_");
//            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
//        }
//        return userReference;
//    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if( email != null){
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

//    public Firebase getMyUserReference(){
//        return getUserReference(getAuthUserEmail());
//    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

//    public Firebase getContactsReference(String email){
//        return getUserReference(email).child(CONTACTS_PATH);
//    }

    public DatabaseReference getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

//    public Firebase getMyContactsReference(){
//        return getContactsReference(getAuthUserEmail());
//    }

    public DatabaseReference getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

//    public Firebase getOneContactReference( String mainEmail,String childEmail  ){
//        String childKey = childEmail.replace(".", "_");
//        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
//    }

    public DatabaseReference getOneContactReference( String mainEmail,String childEmail  ){
        String childKey = childEmail.replace(".", "_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

//    public Firebase getChatsReference(String receiver){
//        String keySender = getAuthUserEmail().replace(".","_");
//        String keyReceiver = receiver.replace(".", "_");
//
//       String keyChat = keySender + SEPARATOR + keyReceiver;
//        if(keySender.compareTo(keyReceiver)>0){
//            keyChat = keyReceiver + SEPARATOR +keySender;
//        }
//        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
//    }

    public DatabaseReference getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if(keySender.compareTo(keyReceiver)>0){
            keyChat = keyReceiver + SEPARATOR +keySender;
        }
        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online){
        if(getMyContactsReference() != null ){
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyContactsReference().updateChildren(updates);
            notifyContactsOnlineConnectionChange(online);
        }
    }

    private void notifyContactsOnlineConnectionChange(boolean online) {
        notifyContactsOnlineConnectionChange(online, false);

    }

    public void signOff(){
        notifyContactsOnlineConnectionChange(User.OFFLINE, true);
    }

//    private void notifyContactsOnlineConnectionChange(final boolean online, final boolean signoff) {
//        final String myEmail = getAuthUserEmail();
//        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot child : dataSnapshot.getChildren()){
//                    String email = child.getKey();
//                    Firebase reference = getOneContactReference(email, myEmail);
//                    reference.setValue(online);
//                }
//                if(signoff){
//                    dataReference.unauth();
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }

    private void notifyContactsOnlineConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if(signoff){
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }
}


