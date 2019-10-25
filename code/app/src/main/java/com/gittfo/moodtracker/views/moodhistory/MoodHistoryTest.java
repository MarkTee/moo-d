package com.gittfo.moodtracker.views.moodhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.gittfo.moodtracker.views.R;


public class MoodHistoryTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history_test);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mood_history);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = {"first one", "second one"};
        MyAdapter mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


        //ListView mood_history_view = findViewById(R.id.mood_history);
        //MoodHistory moodHistory = new MoodHistory();
        //moodHistory.render(this, mood_history_view);
    }
}
