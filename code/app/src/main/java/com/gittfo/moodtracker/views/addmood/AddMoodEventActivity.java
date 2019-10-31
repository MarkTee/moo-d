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

/**
 * Allows the user to create a new MoodEvent, or edit an existing one (by passing a MoodEvent
 * through an intent).
 *
 * @author Mark Thomas
 */
public class AddMoodEventActivity extends AppCompatActivity {

    // Get the current date and time, which are used when creating a new Mood Event
    Date currentDate = new Date();

    // Format date for display
    Format dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    String mDate = dateFormat.format(currentDate);

    // Format time for display
    Format timeFormat = new SimpleDateFormat("h:mm a");
    String mTime = timeFormat.format(currentDate);

    // Buttons representing pre-defined moods that the user may choose from
    private Button happyButton;
    private Button sadButton;
    private Button surprisedButton;
    private Button afraidButton;
    private Button disgustedButton;
    private Button angryButton;
    private List<Button> moodButtons;

    // The user can select a mood by clicking on one of the mood buttons
    private String selectedMood = null;

    /**
     * In the oncreate method, dynamically update the layout as needed.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide ActionBar so that Mood Event entry is full-screen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_mood_event);

        // Display date and time. If creating a new mood event, the current date and time will be
        // shown. If editing an existing mood event, its date and time will be shown.
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

    /**
     * Bring up an AlertDialog that allows the user to abort the activity. Nothing will be saved,
     * and any changes will be lost. Called when the user clicks the exit button in the top-right
     * corner of the screen.
     *
     * @param view - The view that caused the method to be called
     */
    public void exit(View view) {
        new AlertDialog.Builder(AddMoodEventActivity.this)
                .setTitle("Exit Mood Event Creation")
                .setMessage("Are you sure you want to exit?\n\nAny changes will be lost.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    // If the user confirms that they want to exit, finish the activity
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * The user can select a mood by clicking one of the mood buttons. The selected mood button will
     * be highlighted, all other mood buttons will lose colour, and selectedMood will be set to the
     * selected emotional state.
     *
     * @param view - The view that caused the method to be called
     */
    public void selectMoodButton(View view) {
        // deselect all other buttons (remove their colour)
        for (Button button : moodButtons) {
            button.setBackgroundColor(Color.GRAY);
        }

        // Add the appropriate colour to the button that was just clicked
        switch(view.getId()) {
            case R.id.happy_mood_button:
                view.setBackgroundColor(Color.parseColor("#81c784"));
                break;

            case R.id.sad_mood_button:
                view.setBackgroundColor(Color.parseColor("#64b5f6"));
                break;

            case R.id.surprised_mood_button:
                view.setBackgroundColor(Color.parseColor("#fff176"));
                break;

            case R.id.afraid_mood_button:
                view.setBackgroundColor(Color.parseColor("#ffb74d"));
                break;

            case R.id.disgusted_mood_button:
                view.setBackgroundColor(Color.parseColor("#b39ddb"));
                break;

            case R.id.angry_mood_button:
                view.setBackgroundColor(Color.parseColor("#ff8a65"));
                break;
        }

        selectedMood = ((TextView) view).getText().toString(); // save the selected emotional state
    }
}
