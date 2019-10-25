package com.gittfo.moodtracker.views.moodhistory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.mood.MoodHistory;
import com.gittfo.moodtracker.views.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //private String[] mDataset;
    private MoodHistory moodHistory;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout container;
        public MyViewHolder(LinearLayout v) {
            super(v);
            container = v;
        }
        public void populateMoodEventContainer(MoodEvent moodEvent){
            TextView thing1 = container.findViewById(R.id.event_comment);
            thing1.setText(moodEvent.getComment());

            TextView thing2 = container.findViewById(R.id.event_reason);
            thing2.setText(moodEvent.getReason());
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter( MoodHistory mh) {
        this.moodHistory = mh;
        //this.myViewHolder = new MyViewHolder(LinearLayout);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mood_event_container, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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
