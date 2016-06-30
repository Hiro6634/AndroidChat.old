package edu.galileo.android.androidchat.contactlist.ui.adapters;

import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hiro on 30/06/2016.
 */
public interface ImageLoading {
    void load(ImageView imgAvatar, String url);
}
