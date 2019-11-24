package com.gittfo.moodtracker.views;

import android.os.Bundle;

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
    }
}
