package com.gittfo.moodtracker.views.map;

import android.os.Parcel;
import android.os.Parcelable;

import com.gittfo.moodtracker.mood.MoodEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * This class exists only so MainActivity can pass a list of MoodEvents to the MapActivity in  a
 * nice way.
 */
public class MoodHistoryWrapper implements Parcelable {

    // TODO implement reading / writing

    private ArrayList<MoodEvent> moodEventList;

    public MoodHistoryWrapper(ArrayList<MoodEvent> moodEventList) {
        this.moodEventList = moodEventList;
    }

    public MoodHistoryWrapper(Parcel source) {
        this.moodEventList = source.readArrayList(MoodEvent.class.getClassLoader());
    }

    public static final Creator<MoodHistoryWrapper> CREATOR = new Creator<MoodHistoryWrapper>() {
        @Override
        public MoodHistoryWrapper createFromParcel(Parcel source) {
            return new MoodHistoryWrapper(source);
        }

        @Override
        public MoodHistoryWrapper[] newArray(int size) {
            return new MoodHistoryWrapper[size];
        }
    };

    public ArrayList<MoodEvent> getMoodEventList() {
        return moodEventList;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(moodEventList);
    }
}
