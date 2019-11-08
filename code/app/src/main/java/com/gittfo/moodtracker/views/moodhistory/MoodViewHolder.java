package com.gittfo.moodtracker.views.moodhistory;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
    // TODO: make private
    public LinearLayout container;
    // TODO: make private
    public String moodEventID;

    /**
     * Makes a new MoodViewHolder, sets it's container.
     *
     * @param layout the view which will contain a mood event.
     */
    public MoodViewHolder(LinearLayout layout) {
        super(layout);
        container = layout;
    }

    /**
     * Populate the container with the attributes of a mood event.
     *
     * @param moodEvent the moodEvent whose attributes will be shown.
     */
    public void populateMoodEventContainer(MoodEvent moodEvent){
        this.moodEventID = moodEvent.getId();

        // Add the MoodEvent's emotional state
        TextView eventMood = container.findViewById(R.id.user_mood_textView);
        eventMood.setText(moodEvent.getMood().toString());

        // Add the MoodEvent's reason
        TextView eventReason = container.findViewById(R.id.user_reason_textView);
        eventReason.setText(moodEvent.getReason());

        // Add the MoodEvent's location
        TextView eventLocation = container.findViewById(R.id.location_textView);
        eventLocation.setText(moodEvent.getLocation());

        // Add the MoodEvent's date/time
        TextView eventDate = container.findViewById(R.id.date_time_textView);
        eventDate.setText(moodEvent.getDate() != null ? moodEvent.getDate().toString() : "NULL");

        // Add the MoodEvent's social situation
        TextView eventSocialSituation = container.findViewById(R.id.num_people_textView);
        eventSocialSituation.setText(moodEvent.getSocialSituation().toString().toLowerCase());

        // Add the MoodEvent's mood emoticon
        ImageView eventIcon = container.findViewById(R.id.user_emotion_imageView);
        TextView eventColor = container.findViewById(R.id.user_mood_textView);
        ImageView eventEmoticon = container.findViewById(R.id.user_emotion_imageView);

        switch  (moodEvent.getMood()){
            case SAD:
                eventIcon.setImageResource(R.drawable.ic_sad_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonSad));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonSad));
                break;
            case ANGRY:
                eventIcon.setImageResource(R.drawable.ic_angry_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonAngry));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonAngry));
                break;
            case HAPPY:
                eventIcon.setImageResource(R.drawable.ic_happy_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonHappy));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonHappy));
                break;
            case AFRAID:
                eventIcon.setImageResource(R.drawable.ic_afraid_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonAfraid));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonAfraid));
                break;
            case DISGUSTED:
                eventIcon.setImageResource(R.drawable.ic_disgusted_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonDisgusted));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonDisgusted));
                break;
            case SURPRISED:
                eventIcon.setImageResource(R.drawable.ic_surprised_icon_24dp);
                eventColor.setTextColor(ContextCompat.getColor(eventColor.getContext(), R.color.colorEmoticonSurprised));
                eventEmoticon.setColorFilter(ContextCompat.getColor(eventEmoticon.getContext(), R.color.colorEmoticonSurprised));
                break;
        }
    }


}
