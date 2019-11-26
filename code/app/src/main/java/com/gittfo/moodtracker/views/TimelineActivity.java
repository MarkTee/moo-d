package com.gittfo.moodtracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistoryAdapter;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;
import com.gittfo.moodtracker.views.map.MapActivity;
import com.gittfo.moodtracker.views.map.MoodHistoryWrapper;

import java.util.ArrayList;

/**
 * Contains the most recent MoodEvents of a user's followed users
 */
public class TimelineActivity extends AppCompatActivity {

    private RecyclerView moodView;
    private MoodHistoryAdapter moodHistoryAdapter;
    private FilterDialog filterDialog;
    private ArrayList<MoodEvent> followeesMoods;
    private ImageButton dropDownButton;

    private ColorSchemeDialog colorDialog;
    private static int DEFAULT_THEME_ID = R.style.AppTheme;

    protected void onCreate(Bundle savedInstanceState){
        setTheme(DEFAULT_THEME_ID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Hide the ActionBar
        getSupportActionBar().hide();

        // Handle Follow Button
        findViewById(R.id.follow_button).setOnClickListener(v -> sendFollowRequest(v));

        // initialize the mood history
        followeesMoods = new ArrayList<>();

        // setup the RecyclerView
        moodView = findViewById(R.id.followed_moodevents_view);
        moodView.addItemDecoration(new DividerItemDecoration(moodView.getContext(), DividerItemDecoration.VERTICAL));
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        moodView.setLayoutManager(layoutManager);

        moodHistoryAdapter = new MoodHistoryAdapter(this, followeesMoods);
        moodView.setAdapter(moodHistoryAdapter);
        getFromDB();

        filterDialog = new FilterDialog(this);
        findViewById(R.id.toolbar_filter_button).setOnClickListener(v -> filterDialog.show());

        colorDialog = new ColorSchemeDialog(this);
    }

    /**
     * Apply any MoodHistory filters that the user has set
     *
     * @param v The view that the method has been called from
     */
    public void applyFilters(View v) {
//        getFromDB();
        filterDialog.cancel();
    }

    /**
     * Show all of the user's MoodEvents (i.e. clear any MoodHistory filters that the user has set)
     *
     * @param v The view that the method has been called from
     */
    public void showAllMoods(View v) {
        filterDialog.setAllSet();
//        getFromDB();
    }

    /**
     * Get all moods from the database, then update the RecyclerView in order to display them.
     */
    public void getFromDB() {
        // Get all followee's most recent moods from the database
        Log.d("JDB", "Getting Followee's Most Recent Moods");
        Database.get(this).getFolloweeMoods(moods -> {
            for(MoodEvent ev : moods) {
                // add events to the mood history
                if (filterDialog.isFiltered(ev.getMood().ordinal())) {
                    followeesMoods.add(ev);
                }
                Log.d("JDB", ev.toString());
            }
            followeesMoods.sort((b, a) -> a.getDate().compareTo(b.getDate()));
            // Update the RecyclerView so that any new moods can be displayed
            moodHistoryAdapter.notifyDataSetChanged();
        });
    }

    /**
     * Each time the user returns to this activity, update the RecyclerView with moods from the
     * database.
     */
    @Override
    protected void onResume() {
        super.onResume();
//        getFromDB();
    }

    public void sendFollowRequest(View view) {
        FollowDialog followDialog = new FollowDialog(this);
        followDialog.show();
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
     * When the "timeline" button is pressed, don't do anything (since we're already in TimelineActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
    }

    /**
     * When the "inbox" button is pressed, go to the inbox activity.
     * @param view the Inbox button.
     */
    public void startInboxActivity(View view) {
        // don't animate transition between activities
        Intent i = new Intent(this, InboxActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "profile" button is pressed, go to the profile activity.
     * @param view the Profile button.
     */
    public void startProfileActivity(View view) {
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

    public void onChangeColorSchemePressed(){
        colorDialog.show();
    }

    public void validateNewTheme(){
        Intent i = new Intent(this, TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    public void applyColorScheme(View v) {
        int selectedButton = colorDialog.getSelectedNum();
        Log.d("Selected", Integer.toString(selectedButton));

        if (selectedButton == 0) {
            DEFAULT_THEME_ID = R.style.AppTheme;
        } else if (selectedButton == 1) {
            DEFAULT_THEME_ID = R.style.NeonTheme;
        } else if (selectedButton == 2) {
            DEFAULT_THEME_ID = R.style.MonochromeTheme;
        } else if (selectedButton == 3) {
            DEFAULT_THEME_ID = R.style.PastelTheme;
        }

        colorDialog.cancel();
        applyToAllActivities();
        validateNewTheme();
    }

    public void applyToAllActivities() {
        InboxActivity.setDefaultTheme(DEFAULT_THEME_ID);
        MainActivity.setDefaultTheme(DEFAULT_THEME_ID);
        MapActivity.setDefaultTheme(DEFAULT_THEME_ID);
        AddMoodEventActivity.setDefaultTheme(DEFAULT_THEME_ID);
        SigninActivity.setDefaultTheme(DEFAULT_THEME_ID);
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

    public void startSigninActivity(View view){
        Intent i = new Intent(this, SigninActivity.class);
        this.startActivity(i);
    }

    public void startUsernameActivity(View view) {
        Intent i = new Intent(this, ChangeUsernameActivity.class);
        this.startActivity(i);
    }

    public void dropdownPressed(View view){
        dropDownButton = findViewById(R.id.settings_button);
        // create a PopupMenu
        PopupMenu popup = new PopupMenu(TimelineActivity.this, dropDownButton);
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
                        onChangeColorSchemePressed();
                        break;
                    case (R.id.dropdown_three):
                        //TODO: fix log out functionality
                        startSigninActivity(view);
                        break;
                }
                return true;
            }
        });
        popup.show(); // show popup menu
    }
}
