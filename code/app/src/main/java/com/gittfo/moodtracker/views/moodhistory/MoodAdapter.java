package com.gittfo.moodtracker.views.moodhistory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.views.R;

/**
 * Holds onto a mood history, and adapts the RecyclerView.Adapter methods for showing moods.
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    // the mood history we display
    private MoodHistory moodHistory;

    /**
     * Construct a new MoodAdapter.
     * @param mh the mood history this adapter will hold onto.
     */
    public MoodAdapter(MoodHistory mh) {
        this.moodHistory = mh;
    }

    /**
     * Create new views (invoked by the layout manager).
     * @param parent
     * @param viewType
     * @return a new MoodViewHolder
     */
    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mood_event_container, parent, false);

        return new MoodViewHolder(v);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.container.setText(mDataset[position]);
        holder.populateMoodEventContainer(moodHistory.getMoodEvents().get(position));
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     * @return the size of our mood history.
     */
    @Override
    public int getItemCount() {
        return moodHistory.getMoodEvents().size();
    }
}
