package com.gittfo.moodtracker.views.addmood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gittfo.moodtracker.views.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMoodEventActivity extends AppCompatActivity {

    private TextView dateDisplay;
    private TextView timeDisplay;

    // Get the current date and time, which are used to create a Mood Event
    Date currentDate = new Date();

    // Format date for display
    Format dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    String mDate = dateFormat.format(currentDate);

    // Format time for display
    Format timeFormat = new SimpleDateFormat("h:mm a");
    String mTime = timeFormat.format(currentDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide ActionBar so that Mood Entry is full-screen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_mood_event);

        dateDisplay = findViewById(R.id.date_display);
        dateDisplay.setText(mDate);

        timeDisplay = findViewById(R.id.time_display);
        timeDisplay.setText(mTime);


    }
}
