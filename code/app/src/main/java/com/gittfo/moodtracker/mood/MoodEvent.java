package com.gittfo.moodtracker.mood;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;
import java.util.Map;

public class MoodEvent {
    private Mood mood;
    private Date created;

    public MoodEvent(Mood mood, Date created) {
        this.mood = mood;
        this.created = created;
    }

    public String toString() {
        return String.format("%s @ %s", mood.toString(), created.toString());
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public static MoodEvent fromFirebase(QueryDocumentSnapshot document) {
        MoodEvent me = new MoodEvent(
                new Mood(
                        ((Map<String, String>)document.get("mood")).get("emotionalState")
                ),
                document.getDate("created")
        );
        return me;
    }
}
