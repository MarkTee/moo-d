package com.gittfo.moodtracker.views.moodhistory;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;


// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder

/**
 * Provide a reference to the views for each data item. Complex data items may need more than one
 * view per item, and you provide access to all the views for a data item in a view holder.
 */
class MoodViewHolder extends RecyclerView.ViewHolder {

    // layout to hold attributes of a mood event
    private LinearLayout container;

    /**
     * Makes a new MoodViewHolder, sets it's container.
     * @param v the view which will contain a mood event.
     */
    MoodViewHolder(LinearLayout v) {
        super(v);
        container = v;
    }

    /**
     * Populate the container with the attributes of a mood event.
     * @param moodEvent the moodEvent whose attributes will be shown.
     */
    void populateMoodEventContainer(MoodEvent moodEvent){
        // TODO we need to add more to this
        TextView eventComment = container.findViewById(R.id.event_comment);
        eventComment.setText(moodEvent.getReason());

        TextView eventReason = container.findViewById(R.id.event_reason);
        eventReason.setText(moodEvent.getReason());
    }
}
