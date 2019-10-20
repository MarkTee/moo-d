package com.gittfo.moodtracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
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

        //
        ArrayList<MoodEvent> moodEventList = new ArrayList<>();
        MoodHistory moodHistory = new MoodHistory(moodEventList);

        //Populate list
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);
        moodEventList.add(moodEvent);

        //Create listViewAdapter and link it to listView
        MoodHistoryAdapter mhAdapter = new MoodHistoryAdapter(this, moodHistory);
        ListView historyView = findViewById(R.id.mood_history);
        historyView.setAdapter(mhAdapter);
    }
}
