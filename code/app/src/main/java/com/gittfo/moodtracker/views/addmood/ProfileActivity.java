package com.gittfo.moodtracker.views.addmood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.R;

/**
 * An activity for managing a user's profile.
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.user_inbox);  // no profile view yet, just default to this for now
    }
}
