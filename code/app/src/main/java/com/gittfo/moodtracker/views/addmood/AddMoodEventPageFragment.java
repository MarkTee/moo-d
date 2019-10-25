package com.gittfo.moodtracker.views.addmood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gittfo.moodtracker.R;

public class AddMoodEventPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int position = this.getArguments().getInt("position");

        int currentPage = R.layout.mood_entry_screen_1;
        switch (position) {
            case 0:
                currentPage = R.layout.mood_entry_screen_1;
                break;

            case 1:
                currentPage = R.layout.mood_entry_screen_2;
                break;

            case 2:
                currentPage = R.layout.mood_entry_screen_3;
                break;
        }


        ViewGroup rootView = (ViewGroup) inflater.inflate(
                currentPage, container, false);

        return rootView;
    }
}
