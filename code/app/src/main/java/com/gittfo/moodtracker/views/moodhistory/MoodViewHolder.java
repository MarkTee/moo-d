package com.gittfo.moodtracker.views.moodhistory;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;


/**
 * Provide a reference to the views for each data item. Complex data items may need more than one
 * view per item, and you provide access to all the views for a data item in a view holder.
 *
 * Each MoodEvent gets it's own MoodViewHolder, which is what gets displayed in the RecyclerView.
 */
public class MoodViewHolder extends RecyclerView.ViewHolder {

    // layout to hold attributes of a mood event
    private LinearLayout container;

    /**
     * Makes a new MoodViewHolder, sets it's container.
     * @param layout the view which will contain a mood event.
     */
    public MoodViewHolder(LinearLayout layout) {
        super(layout);
        container = layout;
    }

    /**
     * Populate the container with the attributes of a mood event.
     * @param moodEvent the moodEvent whose attributes will be shown.
     */
    public void populateMoodEventContainer(MoodEvent moodEvent){

        TextView eventMood = container.findViewById(R.id.event_mood);
        eventMood.setText(moodEvent.getMood().toString());

        TextView eventReason = container.findViewById(R.id.event_reason);
        eventReason.setText(moodEvent.getReason());

        TextView eventLocation = container.findViewById(R.id.event_location);
        eventLocation.setText(moodEvent.getLocation());

        TextView eventPhoto = container.findViewById(R.id.event_photo);
        eventPhoto.setText(moodEvent.getPhotoReference());

        TextView eventDate = container.findViewById(R.id.event_date);
        eventDate.setText(moodEvent.getDate().toString());

        TextView eventSocialSituation = container.findViewById(R.id.event_social_situation);
        eventSocialSituation.setText(moodEvent.getSocialSituation().toString());
    }
}
