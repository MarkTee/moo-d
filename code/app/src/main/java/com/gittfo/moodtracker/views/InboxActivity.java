package com.gittfo.moodtracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;
import com.gittfo.moodtracker.views.map.MapActivity;
import com.gittfo.moodtracker.views.map.MoodHistoryWrapper;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    private ImageButton dropDownButton;
    private static int DEFAULT_THEME_ID = R.style.AppTheme;

    protected void onCreate(Bundle savedInstanceState){
        setTheme(DEFAULT_THEME_ID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Hide the ActionBar
        getSupportActionBar().hide();
    }

    public void startSigninActivity(View view){
        Intent i = new Intent(this, SigninActivity.class);
        this.startActivity(i);
    }

    public void startUsernameActivity(View view){
        Intent i = new Intent(this, ChangeUsernameActivity.class);
        this.startActivity(i);
    }

    public void dropdownPressed(View view){
        dropDownButton = (ImageButton) findViewById(R.id.settings_button);
        dropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a PopupMenu
                PopupMenu popup = new PopupMenu(com.gittfo.moodtracker.views.InboxActivity.this, dropDownButton);
                // inflate the popup via xml file
                popup.getMenuInflater()
                        .inflate(R.menu.dropdown_menu, popup.getMenu());

                // tie popup to OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()) {
                            case (R.id.dropdown_one):
                                // change username
                                startUsernameActivity(view);
                                break;
                            case (R.id.dropdown_two):
                                // change color scheme
                                //TODO: color scheme change functionality
                                Toast.makeText(com.gittfo.moodtracker.views.InboxActivity.this,
                                        "Coming soon!",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case (R.id.dropdown_three):
                                //TODO: allow for proper log out?
                                startSigninActivity(view);
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); // show popup menu
            }
        }); // close setOnClickListener method

    }

    /**
     * When the New MoodEvent Button (the '+' icon in the bottom-middle of the screen) is clicked,
     * pass the user through to AddMoodEventActivity.
     *
     * @param view The New MoodEvent Button
     */
    public void createMoodEvent(View view) {
        Intent i = new Intent(this, AddMoodEventActivity.class);
        this.startActivity(i);
    }

    /**
     * When the "inbox" button is pressed, don't do anything (since we're already in InboxActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
        // don't animate transition between activities
        Intent i = new Intent(this, TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "inbox" button is pressed, go to the inbox activity.
     * @param view the Inbox button.
     */
    public void startInboxActivity(View view) {
    }

    /**
     * When the "profile" button is pressed, don't do anything (since we're already in MainActivity)
     * @param view the Inbox button.
     */
    public void startProfileActivity(View view){
        // don't animate transition between activities
        Intent i = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "map" button is pressed, go the map-viewing activity.
     * @param view the Map button.
     */
    public void startMapActivity(View view) {
        Intent i = new Intent(this, MapActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    public static void setDefaultTheme(int THEME_ID) {
        DEFAULT_THEME_ID = THEME_ID;
    }

    /**
     * For smoother transitions between activities, disable animations when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
