package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

/**
 * This class implements a dialog that can be used to allow a user to follow another user, in order
 * to stay up to date with their most recent MoodEvent.
 */
public class FollowDialog {
    private AlertDialog followDialog;
    private EditText userFollowEditText;
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
        View layout = inflater.inflate(R.layout.follow_dialog, null);
        builder.setView(layout);

        ImageButton exit_button = layout.findViewById(R.id.exit_button);
        userFollowEditText = layout.findViewById(R.id.user_follow_editText);

        exit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                followDialog.dismiss();
            }
        });

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
