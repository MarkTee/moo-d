package com.gittfo.moodtracker.views.moodhistory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.views.R;

public class MyAdapter extends RecyclerView.Adapter<MoodViewHolder> {
    //private String[] mDataset;
    private MoodHistory moodHistory;



    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter( MoodHistory mh) {
        this.moodHistory = mh;
        //this.myViewHolder = new MoodViewHolder(LinearLayout);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mood_event_container, parent, false);

        MoodViewHolder vh = new MoodViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.container.setText(mDataset[position]);
        holder.populateMoodEventContainer(moodHistory.getMoodEvents().get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return moodHistory.getMoodEvents().size();
    }
}
