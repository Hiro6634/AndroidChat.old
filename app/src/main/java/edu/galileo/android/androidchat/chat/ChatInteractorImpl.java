package edu.galileo.android.androidchat.chat;

/**
 * Created by Hiro on 05/07/2016.
 */
public class ChatInteractorImpl implements ChatInteractor {
    private ChatRepository repository;

    public ChatInteractorImpl() {
        this.repository = new ChatRepositoryImpl();

    }
    @Override
    public void sendMessage(String msg) {
        repository.sendMessage(msg);
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setRecipient(recipient);
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
