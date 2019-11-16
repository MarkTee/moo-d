package com.gittfo.moodtracker.views.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

/**
 * An activity where users can view the locations of a list of MoodEvents on a map. Can be used for
 * an individual's history, complete or filtered, as well as their friends'.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String MOOD_HISTORY_WRAPPER = "MOOD_HISTORY_WRAPPER";
    private ArrayList<MoodEvent> moodEvents;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_map_test);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MoodHistoryWrapper wrapper = bundle.getParcelable(MOOD_HISTORY_WRAPPER);
            moodEvents = wrapper.getMoodEventList();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng last = null;
        for (MoodEvent me : moodEvents) {
            LatLng moodLocation = new LatLng(me.getLatitude(), me.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(moodLocation).title(me.getReason()));
            last = moodLocation;
        }
        if (last != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(last));
        }
    }
}
