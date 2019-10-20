package com.gittfo.moodtracker.mood;

public class Mood {
    String emotionalState;

    public Mood(String emotionalState) {
        this.emotionalState = emotionalState;
    }

    public String toString() {
        return String.format("%s", emotionalState);
    }

    public String getEmotionalState() {
        return emotionalState;
    }

    public void setEmotionalState(String emotionalState) {
        this.emotionalState = emotionalState;
    }
}
