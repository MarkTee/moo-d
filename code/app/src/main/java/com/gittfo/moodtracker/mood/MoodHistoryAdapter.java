package com.gittfo.moodtracker.mood;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;


import com.gittfo.moodtracker.views.R;

import java.util.ArrayList;

public class MoodHistoryAdapter extends ArrayAdapter {
    private ArrayList<MoodEvent> moodHistory;
    private Context context;


    public MoodHistoryAdapter(Context context, MoodHistory moodHistory){
        super(context,0, moodHistory.getMoodEvents());
        this.moodHistory = moodHistory.getMoodEvents();
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null) {
            view = populateMoodEventContainer(
                    LayoutInflater.from(
                            context).inflate(R.layout.mood_event_container,
                            parent,
                            false),
                    moodHistory.get(position));
        }
        return view;
    }

    private View populateMoodEventContainer(View view, MoodEvent moodEvent){
        //TODO: find a better way to do this
        TextView thing1 = view.findViewById(R.id.event_comment);
        thing1.setText(moodEvent.getComment());

        TextView thing2 = view.findViewById(R.id.event_reason);
        thing2.setText(moodEvent.getReason());
        return view;
    }
    
}
