package com.gittfo.moodtracker.views;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.R;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
