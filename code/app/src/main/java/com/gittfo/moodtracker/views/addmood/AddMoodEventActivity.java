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
    private List<Button> moodButtons;

    // Buttons representing pre-defined social situations that the user may choose from
    private List<Button> socialSituationButtons;

    // Use user input to create a new Mood Event
    private String selectedMood = null;
    private String location = null;
    private String reason = null;
    private String selectedSocialSituation = null;
    private String photo = null;

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
        Button happyButton = findViewById(R.id.happy_mood_button);
        Button sadButton = findViewById(R.id.sad_mood_button);
        Button surprisedButton = findViewById(R.id.surprised_mood_button);
        Button afraidButton = findViewById(R.id.afraid_mood_button);
        Button disgustedButton = findViewById(R.id.disgusted_mood_button);
        Button angryButton = findViewById(R.id.angry_mood_button);
        moodButtons = Arrays.asList(happyButton, sadButton, surprisedButton, afraidButton, disgustedButton, angryButton);

        // Initialize Social Situation Buttons
        Button zeroButton = findViewById(R.id.social_button_zero);
        Button oneButton = findViewById(R.id.social_button_one);
        Button twoPlusButton = findViewById(R.id.social_button_two_plus);
        Button crowdButton = findViewById(R.id.social_button_crowd);
        socialSituationButtons = Arrays.asList(zeroButton, oneButton, twoPlusButton, crowdButton);
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
        // Deselect all other buttons (remove their colour)
        for (Button button : moodButtons) {
            button.setBackgroundColor(Color.GRAY);
        }

        // Add the corresponding colour to the button that was just clicked
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
     * The selected social situation button will be highlighted, all other social situation  buttons
     * will lose colour, and selectedSocialSituation will be set to the selected social situation.
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
        switch(view.getId()) {
            case R.id.social_button_zero:
                view.setBackgroundColor(selectedColor);
                break;

            case R.id.social_button_one:
                view.setBackgroundColor(selectedColor);
                break;

            case R.id.social_button_two_plus:
                view.setBackgroundColor(selectedColor);
                break;

            case R.id.social_button_crowd:
                view.setBackgroundColor(selectedColor);
                break;
        }

        selectedSocialSituation = ((TextView) view).getText().toString(); // save the selected emotional state
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
        if (selectedMood == null) {
            new AlertDialog.Builder(AddMoodEventActivity.this)
                    .setTitle("Missing Information")
                    .setMessage("Please select a mood.")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        
        // TODO: Validate user input
        // TODO: Create a new MoodEvent and save it, or save changes to the existing mood event
        finish();
    }
}
