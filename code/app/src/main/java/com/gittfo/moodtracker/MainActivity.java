package com.gittfo.moodtracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.google.common.util.concurrent.Futures;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.get(this).getMoods()
                .addOnSuccessListener(moods -> {
                    System.out.println("GOT MOODS");
                    for(MoodEvent ev : moods) {
                        System.out.println(ev.toString());
                    }
                });
    }

}

