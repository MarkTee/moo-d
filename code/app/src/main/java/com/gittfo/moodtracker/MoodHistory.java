package com.gittfo.moodtracker;

import java.util.ArrayList;

/**
 * Holds a list of moods. This list can be for a single user, or a collection of users (for
 * instance, when viewing the moods of others via the 'follower' system).
 * Also has the ability to render itself for viewing.
 */
public class MoodHistory {

    // The list of moods in this history
    private ArrayList<MoodEvent> moodEvents;

    /**
     * Create a new, empty mood history.
     */
    public MoodHistory() {}

    /**
     * Create a new mood history with a given list of moods.
     * @param moodEvents The list of moods to be held in this history.
     */
    public MoodHistory(ArrayList<MoodEvent> moodEvents) {
        this.moodEvents = moodEvents;
    }

    /**
     * @return The list of mood events in this history.
     */
    public ArrayList<MoodEvent> getMoodEvents() {
        return moodEvents;
    }

    /**
     * @param moodEvent The mood event to add to this history.
     */
    public void addMoodEvent(MoodEvent moodEvent) {
        moodEvents.add(moodEvent);
    }

    /**
     * @param moodEvent The mood event to remove from this history.
     */
    public void removeMoodEvent(MoodEvent moodEvent) {
        moodEvents.remove(moodEvent);
    }

    /**
     * Present this mood history visually, for display in a convenient scrollable format.
     */
    public void render() {

    }
}
