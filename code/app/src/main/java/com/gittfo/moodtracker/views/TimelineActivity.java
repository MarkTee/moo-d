package com.gittfo.moodtracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Contains the most recent MoodEvents of a user's followed users
 */
public class TimelineActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Hide the ActionBar
        getSupportActionBar().hide();

        // Handle Follow Button
        findViewById(R.id.follow_button).setOnClickListener(v -> following(v));
    }

    public void following(View view) {
        FollowDialog followDialog = new FollowDialog(this);
        followDialog.show();
    }

    /**
     * When the "timeline" button is pressed, don't do anything (since we're already in TimelineActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
        ;
    }

    /**
     * When the "inbox" button is pressed, go to the inbox activity.
     * @param view the Inbox button.
     */
    public void startInboxActivity(View view) {
        // don't animate transition between activities
        Intent i = new Intent(this, InboxActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "profile" button is pressed, go to the profile activity.
     * @param view the Profile button.
     */
    public void startProfileActivity(View view){
        // don't animate transition between activities
        Intent i = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }
}
