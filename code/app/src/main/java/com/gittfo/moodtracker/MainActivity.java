package com.gittfo.moodtracker;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exampleListView();
    }

    /**
     * Setup a quick example
     */
    private void exampleListView(){
        Mood mood = new Mood(new Color(),
                "emoticon string",
                Mood.EmotionalState.HAPPY,
                420);

        MoodEvent moodEvent = new MoodEvent("comment",
                "location",
                "photoref",
                "reason",
                new Date(),
                0,
                mood);


        ListView historyView = findViewById(R.id.mood_history);
        MoodHistory moodHistory = new MoodHistory();

        moodHistory.notifyDataSetChanged();

        moodHistory.render(this, historyView);
        moodHistory.addMoodEvent(moodEvent);


    }
}
