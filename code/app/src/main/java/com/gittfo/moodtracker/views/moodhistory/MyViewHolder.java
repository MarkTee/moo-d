package com.gittfo.moodtracker.views.moodhistory;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public LinearLayout thing;
    public MyViewHolder(LinearLayout v) {
        super(v);
        thing = v;
    }
}
