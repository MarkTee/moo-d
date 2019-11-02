package com.gittfo.moodtracker.views.addmood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;
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
    private Date date = new Date();
    // Use user input to create a new Mood Event
    private Mood.EmotionalState mood = null;
    private String reason = "";
    private MoodEvent.SocialSituation socialSituation = null;
    private String photoReference = "";
    private String location = "";
    private MoodEvent moodEvent;

    // Buttons representing pre-defined moods and social situations that the user may choose from
    private List<Button> moodButtons;
    private List<Button> socialSituationButtons;

    // EditText that the user can optionally use to attach a reason to the MoodEvent
    private EditText reasonEditText;

    /**
     * In the oncreate method, dynamically update the layout as needed and initialize views.
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

        // Format date and time for display
        Format dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String mDate = dateFormat.format(date);
        Format timeFormat = new SimpleDateFormat("h:mm a");
        String mTime = timeFormat.format(date);

        // Display date and time. If creating a new mood event, the current date and time will be
        // shown. If editing an existing mood event, its date and time will be shown.
        TextView dateDisplay = findViewById(R.id.date_display);
        dateDisplay.setText(mDate);
        TextView timeDisplay = findViewById(R.id.time_display);
        timeDisplay.setText(mTime);

        // Initialize Mood Buttons
        Button happyButton = findViewById(R.id.happy_mood_button);
        Button sadButton = findViewById(R.id.sad_mood_button);
        Button surprisedButton = findViewById(R.id.surprised_mood_button);
        Button afraidButton = findViewById(R.id.afraid_mood_button);
        Button disgustedButton = findViewById(R.id.disgusted_mood_button);
        Button angryButton = findViewById(R.id.angry_mood_button);
        moodButtons = Arrays.asList(happyButton, sadButton, surprisedButton, afraidButton, disgustedButton, angryButton);

        reasonEditText = findViewById(R.id.reason_entry);

        // Initialize Social Situation Buttons
        Button zeroButton = findViewById(R.id.social_button_zero);
        Button oneButton = findViewById(R.id.social_button_one);
        Button twoPlusButton = findViewById(R.id.social_button_two_plus);
        Button crowdButton = findViewById(R.id.social_button_crowd);
        Button naButton = findViewById(R.id.social_button_na);
        socialSituationButtons = Arrays.asList(zeroButton, oneButton, twoPlusButton, crowdButton, naButton);
    }

    /**
     * Bring up an AlertDialog that allows the user to abort the activity. Nothing will be saved,
     * and any changes will be lost.
     *
     * Called when the user clicks the exit button in the top-right corner of the screen.
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
     * The user can select a mood by clicking one of the mood buttons.
     *
     * The selected mood button will
     * be highlighted, all other mood buttons will lose colour, and the activity's mood attribute
     * will be set accordingly.
     *
     * @param view - The view that caused the method to be called
     */
    public void selectMoodButton(View view) {
        // Deselect all other buttons (remove their colour)
        for (Button button : moodButtons) {
            button.setBackgroundColor(Color.GRAY);
        }

        // Add the corresponding colour to the button that was just clicked, and save the selected mood.
        // TODO: Once we have moods stored somewhere, we'll be able to rebind mood and set colours
        // TODO: more easily.
        switch(view.getId()) {
            case R.id.happy_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_HAPPY.getColor());
                mood = Mood.EmotionalState.HAPPY;
                break;

            case R.id.sad_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_HAPPY.getColor());
                mood = Mood.EmotionalState.SAD;
                break;

            case R.id.surprised_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_SURPRISED.getColor());
                mood = Mood.EmotionalState.SURPRISED;
                break;

            case R.id.afraid_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_AFRAID.getColor());
                mood = Mood.EmotionalState.AFRAID;
                break;

            case R.id.disgusted_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_DISGUISED.getColor());
                mood = Mood.EmotionalState.DISGUSTED;
                break;

            case R.id.angry_mood_button:
                view.setBackgroundColor(Mood.DEFAULT_ANGRY.getColor());
                mood = Mood.EmotionalState.ANGRY;
                break;
        }
    }

    /**
     * When implemented, this method will obtain the user's current GPS coordinates.
     *
     * @param view - The view that caused the method to be called
     */
    public void getLocation(View view) {
        ;
    }

    /**
     * The user can select a social situation by clicking one of the social situation buttons.
     *
     * The selected social situation button will be highlighted, all other social situation buttons
     * will lose colour, and the activity's socialSituation attribute will be set accordingly.
     *
     * @param view - The view that caused the method to be called
     */
    public void selectSocialSituationButton(View view) {
        // Deselect all other buttons (remove their colour)
        for (Button button : socialSituationButtons) {
            button.setBackgroundColor(Color.GRAY);
        }

        // Add colour to the button that was just clicked
        int selectedColor = Color.parseColor("#008577");
        view.setBackgroundColor(selectedColor);

        // Get the text from the selected button
        String clickedSocialButtonText = ((TextView) view).getText().toString();

        // Save the selected emotional state (using one of MoodEvent's pre-defined socialSituations)
        switch (clickedSocialButtonText) {
            case "0":
                socialSituation = MoodEvent.SocialSituation.ZERO;
                break;

            case "1":
                socialSituation = MoodEvent.SocialSituation.ONE;
                break;

            case "2+":
                socialSituation = MoodEvent.SocialSituation.TWOPLUS;
                break;

            case "A Crowd":
                socialSituation = MoodEvent.SocialSituation.CROWD;
                break;

            case "N/A":
                socialSituation = MoodEvent.SocialSituation.NA;
                break;
        }
    }

    /**
     * When implemented, this method will allow the user to optionally attach a photo to the current
     * mood event.
     *
     * @param view - The view that caused the method to be called
     */
    public void addPhoto(View view) {
        ;
    }

    /**
     * If all user input is valid, save the current MoodEvent and return to the previous screen.
     *
     * @param view - The view that caused the method to be called
     */
    public void saveMoodEvent(View view) {
        // Ensure that the user has selected a mood
        if (mood == null) {
            new AlertDialog.Builder(AddMoodEventActivity.this)
                    .setTitle("Missing Information")
                    .setMessage("Please select a mood.")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        // Ensure that the user has selected a social situation
        if (socialSituation == null) {
            new AlertDialog.Builder(AddMoodEventActivity.this)
                    .setTitle("Missing Information")
                    .setMessage("Please select a social situation.")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        reason = reasonEditText.getText().toString();

        // Create the MoodEvent object
        moodEvent = new MoodEvent(location, photoReference, reason, date, socialSituation, mood);

        Database.get(this).addMoodEvent(moodEvent);
        finish();
    }
}
