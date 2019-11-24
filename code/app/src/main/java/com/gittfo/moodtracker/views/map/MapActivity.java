package com.gittfo.moodtracker.views.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;
import com.gittfo.moodtracker.views.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

/**
 * An activity where users can view the locations of a list of MoodEvents on a map. Can be used for
 * an individual's history, complete or filtered, as well as their friends'.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // tag for passing in a mood history wrapper through intent extras
    public static final String MOOD_HISTORY_WRAPPER = "MOOD_HISTORY_WRAPPER";
    // list of mood events to show on the map. source-agnostic, so we can use this for a personal
    // history, or a friend's history, or whatever
    private ArrayList<MoodEvent> moodEvents;
    // level to zoom to once marker is clicked
    private float ONCLICK_ZOOMLVL = 15; // about usual map height
    // our instamce of the googlemap
    private GoogleMap googleMap;
    // keep track of placed markers, since google maps doesn't, and we need to be able to clear them
    private ArrayList<Marker> markers;

    /**
     * Create a new map activity, set the view to the map view and call for a new map to populate
     * it. If we were passed a MoodHistoryWrapper, unwrap it and save the history for displaying.
     *
     * @param savedInstance Reference to the Bundle object passed into the activity
     */
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.maps_screen);

        // we use fragments for now, not a full on map view
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.mood_map);
        mapFragment.getMapAsync(this);

        // get the mood history
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MoodHistoryWrapper wrapper = bundle.getParcelable(MOOD_HISTORY_WRAPPER);
            moodEvents = wrapper.getMoodEventList();
        }

        markers = new ArrayList<>();
    }

    /**
     * When "getMapAsync" returns a GoogleMap, populate it with our MoodHistory locations.
     *
     * @param googleMap the Map to display mood events on.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMarkerClickListener(this);
        showMoodEvents(moodEvents);
    }

    private void onMyLocations(View view) {
        Database.get(this).getMoods().addOnSuccessListener(moods -> {
            moodEvents.clear();
            for(MoodEvent ev : moods) {
                // add events to the mood history
                moodEvents.add(ev);
            }
            moodEvents.sort((b, a) -> a.getDate().compareTo(b.getDate()));
            showMoodEvents(moodEvents);
        });
    }

    private BitmapDescriptor fromDrawable(int id, int color) {
        Drawable drawable = ContextCompat.getDrawable(this, id);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setTint(color);
        //drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public void showMoodEvents(List<MoodEvent> moodEventList) {
        // clear out the current markers
        for (Marker m : markers) {
            m.remove();
        }
        markers.clear();

        // for aesthetics, move the camera to the most recent mood event
        LatLng last = null;
        for (MoodEvent moodEvent : moodEventList) {
            LatLng moodLocation = new LatLng(moodEvent.getLatitude(), moodEvent.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.title("title");

            markerOptions.position(new LatLng(moodEvent.getLatitude(), moodEvent.getLongitude()));
            Mood mood = Mood.moodFromEmotionalState(moodEvent.getMood());

            markerOptions.icon(fromDrawable(mood.getEmoticon(), mood.getColor()));

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(moodEvent);

            markers.add(marker);

            last = moodLocation;
        }
        if (last != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(last));
        }
    }

    public String posToString(LatLng pos) {
        double lat = pos.latitude;
        double lon = pos.longitude;

        return String.format("%.2f, %.2f", lat, lon);
    }

    public boolean onMarkerClick(final Marker marker) {

        MoodEvent moodEvent = (MoodEvent) marker.getTag();

        TextView usernameView = findViewById(R.id.map_user_name);
        usernameView.setText(Database.get(this).getUserName());

        TextView location = findViewById(R.id.map_location_text);
        location.setText(posToString(marker.getPosition()));

        ImageView emoticonView = findViewById(R.id.map_emoticon);

        Mood mood = Mood.moodFromEmotionalState(moodEvent.getMood());
        emoticonView.setImageResource(mood.getEmoticon());
        emoticonView.setColorFilter(mood.getColor());

        TextView moodText = findViewById(R.id.map_mood_text);
        moodText.setTextColor(mood.getColor());
        moodText.setText(moodEvent.getMood().toString().toLowerCase());

        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(marker.getPosition(), ONCLICK_ZOOMLVL));

        return true;
    }
}
