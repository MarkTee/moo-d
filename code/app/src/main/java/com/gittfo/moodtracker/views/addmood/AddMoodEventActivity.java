package com.gittfo.moodtracker.views.addmood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gittfo.moodtracker.views.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddMoodEventActivity extends AppCompatActivity {

    // Get the current date and time, which are used to create a Mood Event
    Date currentDate = new Date();

    // Format date for display
    Format dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    String mDate = dateFormat.format(currentDate);

    // Format time for display
    Format timeFormat = new SimpleDateFormat("h:mm a");
    String mTime = timeFormat.format(currentDate);

    private Button happyButton;
    private Button sadButton;
    private Button surprisedButton;
    private Button afraidButton;
    private Button disgustedButton;
    private Button angryButton;
    private List<Button> moodButtons;

    private String selectedMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide ActionBar so that Mood Entry is full-screen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_mood_event);

        TextView dateDisplay = findViewById(R.id.date_display);
        dateDisplay.setText(mDate);

        TextView timeDisplay = findViewById(R.id.time_display);
        timeDisplay.setText(mTime);

        // Initialize Mood Buttons
        happyButton = findViewById(R.id.happy_mood_button);
        sadButton = findViewById(R.id.sad_mood_button);
        surprisedButton = findViewById(R.id.surprised_mood_button);
        afraidButton = findViewById(R.id.afraid_mood_button);
        disgustedButton = findViewById(R.id.disgusted_mood_button);
        angryButton = findViewById(R.id.angry_mood_button);
        moodButtons = Arrays.asList(happyButton, sadButton, surprisedButton, afraidButton, disgustedButton, angryButton);
    }

    public void exit(View view) {
        new AlertDialog.Builder(AddMoodEventActivity.this)
                .setTitle("Exit Mood Event Creation")
                .setMessage("Are you sure you want to exit?\n\nAny changes will be lost.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void selectMoodButton(View view) {
        // deselect all other buttons
        for (Button button : moodButtons) {
            button.setBackgroundColor(Color.GRAY);
        }

        switch(view.getId()) {
            case R.id.happy_mood_button:
                view.setBackgroundColor(Color.parseColor("#81c784"));
                selectedMood = "Happy";
                break;

            case R.id.sad_mood_button:
                view.setBackgroundColor(Color.parseColor("#64b5f6"));
                selectedMood = "Sad";
                break;

            case R.id.surprised_mood_button:
                view.setBackgroundColor(Color.parseColor("#fff176"));
                selectedMood = "Surprised";
                break;

            case R.id.afraid_mood_button:
                view.setBackgroundColor(Color.parseColor("#ffb74d"));
                selectedMood = "Afraid";
                break;

            case R.id.disgusted_mood_button:
                view.setBackgroundColor(Color.parseColor("#b39ddb"));
                selectedMood = "Disgusted";
                break;

            case R.id.angry_mood_button:
                view.setBackgroundColor(Color.parseColor("#ff8a65"));
                selectedMood = "Angry";
                break;
        }
    }
}
