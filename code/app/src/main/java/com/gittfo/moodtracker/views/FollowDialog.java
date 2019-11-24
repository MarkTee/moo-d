package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

/**
 * This class implements a dialog that can be used to allow a user to follow another user, in order
 * to stay up to date with their most recent MoodEvent.
 */
public class FollowDialog {
    private AlertDialog followDialog;
    Activity c;

    /**
     * Create a new FollowDialog object
     *
     * @param c The activity context that the dialog will be created in
     */
    public FollowDialog(Activity c) {
        this.c = c;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = c.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.follow_dialog, null));
        Log.d("JUI", "Making builder");
        followDialog = builder.create();
    }

    /**
     * Display the FollowDialog and set onClickListener for the "Send Request" button.
     */
    public void show() {
        followDialog.show();
    }
}
