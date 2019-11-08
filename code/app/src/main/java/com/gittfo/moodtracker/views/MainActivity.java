package com.gittfo.moodtracker.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.mood.MoodHistoryAdapter;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;

public class MainActivity extends AppCompatActivity {

    private MoodHistory moodHistory;
    private RecyclerView moodView;
    private MoodHistoryAdapter moodHistoryAdapter;

    private AlertDialog filterDialog;
    private boolean[] filterState;

    /**
     * Each time the user returns to this activity, update the RecyclerView with moods from the
     * database.
     */
    @Override
    protected void onResume() {
        super.onResume();
        getFromDB();
    }

    /**
     * In the oncreate method, dynamically update the layout as needed and initialize views.
     *
     * @param savedInstanceState Reference to the Bundle object passed into the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the mood history
        moodHistory = new MoodHistory(null);

        // setup the RecyclerView
        moodView = findViewById(R.id.mood_history_view);
        moodView.addItemDecoration(new DividerItemDecoration(moodView.getContext(), DividerItemDecoration.VERTICAL));
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        moodView.setLayoutManager(layoutManager);

        moodHistoryAdapter = new MoodHistoryAdapter(moodHistory);
        moodView.setAdapter(moodHistoryAdapter);
        moodHistory.setMoodHistoryAdapter(moodHistoryAdapter);
        moodHistory.render(this, moodView);

        filterState = new boolean[]{true, true, true, true, true, true};
        findViewById(R.id.timeline_menu_item).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.mood_filter, null));
            builder.setTitle("Filter Moods");
            Log.d("JUI", "Making builder");
            filterDialog = builder.create();
            filterDialog.show();
            updateFilterUI();
        });

    }

    public void selectMoodButton(View v) {
        Log.d("JUI", "Selected Button");
        int index;
        if (v.getId() == R.id.happy_mood_button) {
            index = MoodEvent.HAPPY_INDEX;
        } else if (v.getId() == R.id.sad_mood_button) {
            index = MoodEvent.SAD_INDEX;
        } else if (v.getId() == R.id.angry_mood_button) {
            index = MoodEvent.AFRAID_INDEX;
        } else if (v.getId() == R.id.afraid_mood_button) {
            index = MoodEvent.ANGRY_INDEX;
        } else if (v.getId() == R.id.disgusted_mood_button) {
            index = MoodEvent.DISGUSTED_INDEX;
        } else if (v.getId() == R.id.surprised_mood_button) {
            index = MoodEvent.SURPRISED_INDEX;
        } else {
            index = 0;
            Log.d("JUI", "Error, invalid view, assuming happy");
        }
        Log.d("JUI", "Picked a view: " + index);
        filterState[index] ^= true;
        updateFilterUI();
    }

    private void updateFilterUI() {
        // The order is important here. This isn't ideal, it needs
        // to reflect the MOOD_INDEX values in MoodEvent
        View[] buttons = new View[]{
                filterDialog.findViewById(R.id.happy_mood_button),
                filterDialog.findViewById(R.id.sad_mood_button),
                filterDialog.findViewById(R.id.surprised_mood_button),
                filterDialog.findViewById(R.id.angry_mood_button),
                filterDialog.findViewById(R.id.disgusted_mood_button),
                filterDialog.findViewById(R.id.afraid_mood_button),
        };

        ColorStateList[] tints = new ColorStateList[]{
                ContextCompat.getColorStateList(this, R.color.colorHappy),
                ContextCompat.getColorStateList(this, R.color.colorSad),
                ContextCompat.getColorStateList(this, R.color.colorSurprised),
                ContextCompat.getColorStateList(this, R.color.colorAngry),
                ContextCompat.getColorStateList(this, R.color.colorDisgusted),
                ContextCompat.getColorStateList(this, R.color.colorAfraid)
        };
        ColorStateList notSelectedTint = ContextCompat.getColorStateList(this, R.color.design_default_color_background);

        for (int i = 0; i < 6; i++){
            buttons[i].setBackgroundTintList(filterState[i] ? tints[i] : notSelectedTint);
        }
    }

    public void applyFilters(View v) {
        getFromDB();
        filterDialog.cancel();
    }

    /**
     * Get all moods from the database, then update the RecyclerView in order to display them.
     */
    public void getFromDB() {
        // Get all moods from the database
        Log.d("JDB", "Getting Moods");
        Database.get(this).getMoods().addOnSuccessListener(moods -> {
            moodHistory.clear();
            for(MoodEvent ev : moods) {
                // add events to the mood history
                if (filterState[ev.getMood().ordinal()])
                    moodHistory.addMoodEvent(ev);
                Log.d("JDB", ev.toString());
            }
            // Update the RecyclerView so that any new moods can be displayed
            moodHistory.notifyDataSetChanged();
        });
        Log.d("JDB", "Got Moods");
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
}
