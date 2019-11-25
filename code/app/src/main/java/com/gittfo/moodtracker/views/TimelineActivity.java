package com.gittfo.moodtracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistoryAdapter;

import java.util.ArrayList;

/**
 * Contains the most recent MoodEvents of a user's followed users
 */
public class TimelineActivity extends AppCompatActivity {

    private RecyclerView moodView;
    private MoodHistoryAdapter moodHistoryAdapter;
    private FilterDialog filterDialog;
    private ArrayList<MoodEvent> followeesMoods;

    protected void onCreate(Bundle savedInstanceState){
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
    }

    /**
     * Apply any MoodHistory filters that the user has set
     *
     * @param v The view that the method has been called from
     */
    public void applyFilters(View v) {
        getFromDB();
        filterDialog.cancel();
    }

    /**
     * Show all of the user's MoodEvents (i.e. clear any MoodHistory filters that the user has set)
     *
     * @param v The view that the method has been called from
     */
    public void showAllMoods(View v) {
        filterDialog.setAllSet();
        getFromDB();
    }

    /**
     * Get all moods from the database, then update the RecyclerView in order to display them.
     */
    public void getFromDB() {
        // Get all moods from the database
        Log.d("JDB", "Getting Moods");
        Database.get(this).getMoods().addOnSuccessListener(moods -> {
            followeesMoods.clear();
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
        Log.d("JDB", "Got Moods");
    }

    /**
     * Each time the user returns to this activity, update the RecyclerView with moods from the
     * database.
     */
    @Override
    protected void onResume() {
        super.onResume();
        getFromDB();
    }

    public void sendFollowRequest(View view) {
        FollowDialog followDialog = new FollowDialog(this);
        followDialog.show();
    }

    /**
     * When the "timeline" button is pressed, don't do anything (since we're already in TimelineActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
        ;
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
    public void startProfileActivity(View view){
        // don't animate transition between activities
        Intent i = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
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
