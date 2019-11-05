package com.gittfo.moodtracker.views.moodhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.views.R;

import java.util.Date;


public class MoodHistoryTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history_test);


        /*
        RecyclerView recyclerView = findViewById(R.id.mood_history);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        //ListView mood_history_view = findViewById(R.id.mood_history);
        MoodHistory moodHistory = new MoodHistory();

        MoodEvent me = new MoodEvent(
                "location",
                "photoref",
                "reason",
                new Date(),
                MoodEvent.SocialSituation.CROWD,
                Mood.EmotionalState.AFRAID
                );

        moodHistory.addMoodEvent(me);

        MoodAdapter moodAdapter = new MoodAdapter(moodHistory);
        recyclerView.setAdapter(moodAdapter);

         */
    }
}
