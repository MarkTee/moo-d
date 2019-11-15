package com.gittfo.moodtracker.views.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.gittfo.moodtracker.mood.MoodEvent;

import java.util.List;

/**
 * This class exists only so MainActivity can pass a list of MoodEvents to the MapActivity in  a
 * nice way.
 */
public class MoodHistoryWrapper implements Parcelable {

    // TODO implement reading / writing

    private List<MoodEvent> moodEventList;

    public MoodHistoryWrapper(List<MoodEvent> moodEventList) {
        this.moodEventList = moodEventList;
    }

    public List<MoodEvent> getMoodEventList() {
        return moodEventList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(moodEventList);
    }
}
