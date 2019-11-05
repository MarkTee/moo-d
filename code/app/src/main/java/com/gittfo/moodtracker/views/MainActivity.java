package com.gittfo.moodtracker.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.mood.MoodHistoryAdapter;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;

public class MainActivity extends AppCompatActivity {

    private static MoodHistory moodHistory;
    private static RecyclerView moodView;
    private static MoodHistoryAdapter moodHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the mood history
        if (moodHistory == null) {
            moodHistory = new MoodHistory();
        }

        Log.d("JDB", "Getting Moods");
         Database.get(this).getMoods().addOnSuccessListener(moods -> {
                     for(MoodEvent ev : moods) {
                         // add events to the mood history
                         moodHistory.addMoodEvent(ev);
                         moodHistoryAdapter.notifyDataSetChanged();
                         Log.d("JDB", ev.toString());
                     }
                 });
        Log.d("JDB", "Got Moods");


        if (moodView == null) {
            moodView = findViewById(R.id.mood_history_view);
            moodView.addItemDecoration(new DividerItemDecoration(moodView.getContext(), DividerItemDecoration.VERTICAL));
            // use a linear layout manager
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            moodView.setLayoutManager(layoutManager);
        }

        moodHistoryAdapter = new MoodHistoryAdapter(moodHistory);
        moodView.setAdapter(moodHistoryAdapter);
    }

    public void createMoodEvent(View view) {
        Intent i = new Intent(this, AddMoodEventActivity.class);
        this.startActivity(i);
    }

    public static void addMoodEvent(MoodEvent moodEvent) {
        moodHistory.addMoodEvent(moodEvent);
        moodHistoryAdapter = new MoodHistoryAdapter(moodHistory);
        moodView.setAdapter(moodHistoryAdapter);
    }
}
