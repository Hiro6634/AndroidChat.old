package edu.galileo.android.androidchat.chat;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.galileo.android.androidchat.R;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.domain.AvatarHelper;
import edu.galileo.android.androidchat.entities.ChatMessage;
import edu.galileo.android.androidchat.lib.GlideImageLoader;
import edu.galileo.android.androidchat.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView{

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @Bind(R.id.editTxtMessage)
    EditText editTxtMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;

    private ChatAdapter adapter;
    private ChatPresenter presenter;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();
        setupToolBar(getIntent());
    }

    private void setupAdapter() {

    }

    private void setupRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        messageRecyclerView.scrollToPosition(adapter.getItemCount()-1);
    }

    public void setupToolBar(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online":"offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
    }
}
