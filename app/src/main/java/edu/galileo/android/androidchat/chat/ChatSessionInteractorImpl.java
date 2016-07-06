package edu.galileo.android.androidchat.chat;

/**
 * Created by Hiro on 05/07/2016.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    private ChatRepository repository;

    public ChatSessionInteractorImpl() {
        this.repository = new ChatRepositoryImpl();

    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
