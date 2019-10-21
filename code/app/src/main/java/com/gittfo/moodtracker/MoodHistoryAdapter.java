package com.gittfo.moodtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;



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
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.mood_event_container, parent,false);
        }

        MoodEvent moodE = moodHistory.get(position);
        TextView thing1 = view.findViewById(R.id.thing1);
        TextView thing2 = view.findViewById(R.id.thing2);
        thing1.setText(moodE.getComment());
        thing2.setText(moodE.getReason());

        return view;
    }



}
