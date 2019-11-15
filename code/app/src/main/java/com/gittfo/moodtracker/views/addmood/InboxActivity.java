package com.gittfo.moodtracker.views.addmood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.R;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.user_inbox);
    }
}
