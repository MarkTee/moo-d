package com.gittfo.moodtracker.mood;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;


import com.gittfo.moodtracker.views.R;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;
import com.gittfo.moodtracker.views.moodhistory.MoodViewHolder;


public class MoodHistoryAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private MoodHistory moodHistory;


    public MoodHistoryAdapter(MoodHistory moodHistory){
        this.moodHistory = moodHistory;
    }

    /*
    @NonNull
    //@Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null) {
            view = populateMoodEventContainer(
                    LayoutInflater.from(
                            MainActivity.getContext()).inflate(R.layout.mood_event_container,
                            parent,
                            false),
                    moodHistory.getMoodEvents().get(position));
        }
        return view;
    }
    */


    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mood_event, parent, false);

        return new MoodViewHolder(v);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.container.setText(mDataset[position]);
        holder.populateMoodEventContainer(moodHistory.getMoodEvents().get(position));
        holder.container.setOnClickListener(v -> {
            Context c = moodHistory.getContext();
            Intent i = new Intent(c, AddMoodEventActivity.class);
            i.putExtra(AddMoodEventActivity.EDIT_MOOD, holder.moodEventID);
            c.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return moodHistory.getMoodEvents().size();
    }
}
