package com.gittfo.moodtracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Hide the ActionBar
        getSupportActionBar().hide();
    }

    /**
     * When the "inbox" button is pressed, don't do anything (since we're already in InboxActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
        // don't animate transition between activities
        Intent i = new Intent(this, TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "inbox" button is pressed, go to the inbox activity.
     * @param view the Inbox button.
     */
    public void startInboxActivity(View view) {
        ;
    }

    /**
     * When the "profile" button is pressed, don't do anything (since we're already in MainActivity)
     * @param view the Inbox button.
     */
    public void startProfileActivity(View view){
        // don't animate transition between activities
        Intent i = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }
}
