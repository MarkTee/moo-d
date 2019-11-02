package com.gittfo.moodtracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Log.d("JDB", "Getting Moods");
         Database.get(this).getMoods().addOnSuccessListener(moods -> {
                     for(MoodEvent ev : moods) {
                         // TODO: add these to the mood history
                         Log.d("JDB", ev.toString());
                     }
                 });
        Log.d("JDB", "Got Moods");
    }

    public void createMoodEvent(View view) {
        Intent i = new Intent(this, AddMoodEventActivity.class);
        this.startActivity(i);
    }
}
