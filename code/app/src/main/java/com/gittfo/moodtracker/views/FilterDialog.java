package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.gittfo.moodtracker.mood.MoodEvent;

import java.util.Arrays;

public class FilterDialog {
    private AlertDialog filterDialog;
    private boolean[] filterState;
    Activity c;
    int selectedCount = 0;

    public FilterDialog(Activity c) {
        this.c = c;
        filterState = new boolean[]{true, true, true, true, true, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = c.getLayoutInflater();


        builder.setView(inflater.inflate(R.layout.mood_filter, null));
        builder.setTitle("Filter Moods");
        Log.d("JUI", "Making builder");
        filterDialog = builder.create();
    }

    public void show() {
        filterDialog.show();
        filterDialog.findViewById(R.id.happy_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        filterDialog.findViewById(R.id.sad_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        filterDialog.findViewById(R.id.surprised_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        filterDialog.findViewById(R.id.angry_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        filterDialog.findViewById(R.id.disgusted_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        filterDialog.findViewById(R.id.afraid_mood_button).setOnClickListener(v -> this.selectMoodButton(v));
        updateFilterUI();
    }

    public void selectMoodButton(View v) {
        Log.d("JUI", "Selected Button");
        int index;
        if (v.getId() == R.id.happy_mood_button) {
            index = MoodEvent.HAPPY_INDEX;
        } else if (v.getId() == R.id.sad_mood_button) {
            index = MoodEvent.SAD_INDEX;
        } else if (v.getId() == R.id.angry_mood_button) {
            index = MoodEvent.AFRAID_INDEX;
        } else if (v.getId() == R.id.afraid_mood_button) {
            index = MoodEvent.ANGRY_INDEX;
        } else if (v.getId() == R.id.disgusted_mood_button) {
            index = MoodEvent.DISGUSTED_INDEX;
        } else if (v.getId() == R.id.surprised_mood_button) {
            index = MoodEvent.SURPRISED_INDEX;
        } else {
            index = 0;
            Log.d("JUI", "Error, invalid view, assuming happy");
        }
        Log.d("JUI", "Picked a view: " + index);
        if (isAllSet() && selectedCount == 0) {
            selectedCount++;
            for (int i = 0; i < 6; i++) {
                filterState[i] = i == index;
            }
        } else {
            selectedCount += filterState[index] ? -1 : 1;
            filterState[index] ^= true;
        }
        if (isNoneSet()) {
            for (int i = 0; i < 6; i++) {
                filterState[i] = true;
            }
        }
        Log.d("JUI", isNoneSet()+":"+Arrays.toString(filterState)+":"+isAllSet()+"sc="+selectedCount);
        updateFilterUI();
    }

    private void updateFilterUI() {
        // The order is important here. This isn't ideal, it needs
        // to reflect the MOOD_INDEX values in MoodEvent
        View[] buttons = new View[]{
                filterDialog.findViewById(R.id.happy_mood_button),
                filterDialog.findViewById(R.id.sad_mood_button),
                filterDialog.findViewById(R.id.surprised_mood_button),
                filterDialog.findViewById(R.id.angry_mood_button),
                filterDialog.findViewById(R.id.disgusted_mood_button),
                filterDialog.findViewById(R.id.afraid_mood_button),
        };

        ColorStateList[] tints = new ColorStateList[]{
                ContextCompat.getColorStateList(c, R.color.colorHappy),
                ContextCompat.getColorStateList(c, R.color.colorSad),
                ContextCompat.getColorStateList(c, R.color.colorSurprised),
                ContextCompat.getColorStateList(c, R.color.colorAngry),
                ContextCompat.getColorStateList(c, R.color.colorDisgusted),
                ContextCompat.getColorStateList(c, R.color.colorAfraid)
        };
        ColorStateList notSelectedTint = ContextCompat.getColorStateList(c, R.color.design_default_color_background);

        boolean allSet = isAllSet();
        for (int i = 0; i < 6; i++) {
            buttons[i].setBackgroundTintList(filterState[i] && (selectedCount == 6 || !allSet) ? tints[i] : notSelectedTint);
        }
    }

    public boolean isAllSet() {
        boolean allSet = true;
        for (int i = 0; i < 6; i++){
            allSet &= filterState[i];
        }
        return allSet;
    }

    public boolean isNoneSet() {
        for (int i = 0; i < 6; i++){
            if (filterState[i]) {
                return false;
            }
        }
        return true;
    }

    public void cancel() {
        filterDialog.cancel();
    }

    public boolean isFiltered(int ordinal) {
        return this.filterState[ordinal];
    }
}
