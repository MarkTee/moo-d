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
     *
     * @param mh the mood history this adapter will hold onto.
     */
    public MoodAdapter(MoodHistory mh) {
        this.moodHistory = mh;
    }

    /**
     * Create new views (invoked by the layout manager).
     *
     * @param parent The parent ViewGroup for this view
     * @param viewType The type of view
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
     *
     * @param holder The holder for this View
     * @param position The position of the MoodEvent in the holder
     */
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        // Get the MoodEvent at the given position, then populate its contents with data
        holder.populateMoodEventContainer(moodHistory.getMoodEvents().get(position));
    }

    /**
     * Return the size of MoodHistory.
     * @return the size of our mood history.
     */
    @Override
    public int getItemCount() {
        return moodHistory.getMoodEvents().size();
    }
}
