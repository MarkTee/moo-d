package com.gittfo.moodtracker.views.moodhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.gittfo.moodtracker.R;

public class MoodHistoryTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history_test);

        ListView mood_history_view = findViewById(R.id.mood_history);
        MoodHistory moodHistory = new MoodHistory();
        moodHistory.render(this, mood_history_view);
    }
}
