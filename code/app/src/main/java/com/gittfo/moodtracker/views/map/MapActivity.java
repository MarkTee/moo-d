package com.gittfo.moodtracker.views.map;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity where users can view the locations of a list of MoodEvents on a map. Can be used for
 * an individual's history, complete or filtered, as well as their friends'.
 */
public class MapActivity extends AppCompatActivity {

    // tag that goes along with the passed-in MoodEvent list.
    public static final String MOOD_HISTORY = "MOOD_HISTORY";
    private List<MoodEvent> moodEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }
}
