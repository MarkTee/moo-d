package com.gittfo.moodtracker.views.map;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * An activity where users can view the locations of a list of MoodEvents on a map. Can be used for
 * an individual's history, complete or filtered, as well as their friends'.
 */
public class MapActivity extends AppCompatActivity {

    public static final String MOOD_HISTORY_WRAPPER = "MOOD_HISTORY_WRAPPER";
    private ArrayList<MoodEvent> moodEvents;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_map);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MoodHistoryWrapper wrapper = bundle.getParcelable(MOOD_HISTORY_WRAPPER);
            moodEvents = wrapper.getMoodEventList();
        }
    }
}
