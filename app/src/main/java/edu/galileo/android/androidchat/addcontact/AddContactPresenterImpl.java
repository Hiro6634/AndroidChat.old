package edu.galileo.android.androidchat.addcontact;

import android.app.usage.UsageEvents;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.androidchat.addcontact.event.AddContactEvent;
import edu.galileo.android.androidchat.addcontact.ui.AddContactFragment;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Hiro on 03/07/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;
    private EventBus eventBus;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if( view != null){
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if(view != null){
            view.hideProgress();
            view.showInput();

            if(event.isError()){
                view.contactNotAdded();
            }
            else {
                view.contactAdded();
            }
        }
    }
}












