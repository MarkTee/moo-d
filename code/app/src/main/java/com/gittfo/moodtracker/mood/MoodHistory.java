package com.gittfo.moodtracker.mood;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Holds a list of moods. This list can be for a single user, or a collection of users (for
 * instance, when viewing the moods of others via the 'follower' system).
 * Also has the ability to render itself for viewing.
 */
public class MoodHistory {

    // The list of moods in this history
    private ArrayList<MoodEvent> moodEvents;
    private Context c;

    public void setMoodHistoryAdapter(MoodHistoryAdapter moodHistoryAdapter) {
        this.moodHistoryAdapter = moodHistoryAdapter;
    }

    // Custom ArrayAdapter
    private MoodHistoryAdapter moodHistoryAdapter;

    /**
     * Create a new, empty mood history.
     */
    public MoodHistory(MoodHistoryAdapter mha) {
        this(mha, new ArrayList<>());
    }

    /**
     * Create a new mood history with a given list of moods.
     *
     * @param moodEvents The list of moods to be held in this history.
     */
    public MoodHistory(MoodHistoryAdapter mha, ArrayList<MoodEvent> moodEvents) {
        this.moodEvents = moodEvents;
        moodHistoryAdapter = mha;
    }

    /**
     * Returns an ArrayList containing the MoodEvents in this MoodHistory
     *
     * @return The list of mood events in this history.
     */
    public ArrayList<MoodEvent> getMoodEvents() {
        return moodEvents;
    }

    /**
     * Adds a MoodEvent to this MoodHistory
     *
     * @param moodEvent The mood event to add to this history.
     */
    public void addMoodEvent(MoodEvent moodEvent) {
        moodEvents.add(0, moodEvent);
    }

    /**
     * Removes all mood events from this MoodHistory
     */
    public void clear() {
        this.moodEvents.clear();
    }

    /**
     * Removes a single MoodEvent from this MoodHistory
     *
     * @param moodEvent The mood event to remove from this history.
     */
    public void removeMoodEvent(MoodEvent moodEvent) {
        moodEvents.remove(moodEvent);
    }

    /**
     * Present this mood history visually in a RecyclerView. We might not actually need this.
     *
     * @param context Activity context to render in
     * @param recyclerView View object to render with
     *
     *<pre>
     *<code>
     *         // in MainActivity Classs 
     *         ListView historyView = findViewById(R.id.mood_history);
     *         MoodHistory moodHistory = new MoodHistory();
     *         moodHistory.render(this, historyView);
     *</code>
     *</pre>
     *
     */
    public void render(Context context, RecyclerView recyclerView) {
        // View object
        recyclerView.setAdapter(moodHistoryAdapter);
        c = context;
    }

    /**
     * Expose ArrayAdapter.notifyDataSetChanged() call.
     * do nothing if MoodHistory hasnt been rendered yet
     */
    public void notifyDataSetChanged(){
        this.moodEvents.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        if(moodHistoryAdapter != null) {
            moodHistoryAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Get the context of this MoodHistory (i.e. the activity context that the MoodHistory has been
     * rendered in).
     *
     * @return The activity context that the MoodHistory has been rendered in
     */
    public Context getContext() {
        return c;
    }
}
