package com.gittfo.moodtracker.mood;

import android.graphics.Color;

/**
 * Represents the notion of a 'Mood' for our mood tracker app. Stores both information related to
 * the state of a mood (i.e. emotion) and the representation of a mood (i.e. color, emoticon).
 */
public class Mood {

    /**
     * The list of possible emotional states a user can choose for their moods.
     * Based on the "six universal moods" as classified by Paul Ekman, with the addition of "tired,
     * lonely" and "meh", as suggested by Chuck.
     * Subject to change.
     */
    public enum EmotionalState{
        HAPPY,
        SAD,
        ANGRY,
        DISGUSTED,
        AFRAID,
        SURPRISED,
        TIRED,
        LONELY,
        MEH
    };

    // Moods will be assigned a consistent color in the app, i.e. angry -> red
    private Color color;
    // Filename of emoticon representing this mood. Will save on memory by not storing a copy
    private String emoticon;
    // Enum holding the "emotional state" this mood represents
    private EmotionalState emotionalState;
    // The "value" of this mood
    private int value;

    /**
     * Creates a new mood, typically to be added to a mood history.
     * @param color The color of this mood, should be consistent across the app.
     * @param emoticon Filname of the icon representing this mood.
     * @param emotionalState Enum describing the emotional state of this mood
     * @param value Value of this mood.
     */
    public Mood(Color color, String emoticon, EmotionalState emotionalState, int value) {
        this.color = color;
        this.emoticon = emoticon;
        this.emotionalState = emotionalState;
        this.value = value;
    }

    /**
     * @return The color of this mood.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color Set the color of this mood. Might remove method in future, not sure why colors
     *              would need to change.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return The filename for the icon representing this mood.
     */
    public String getEmoticon() {
        return emoticon;
    }

    /**
     * @param emoticon Filename for the icon representing this mood.
     */
    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    /**
     * @return An enum describing the emotional state of this mood.
     */
    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    /**
     * @param emotionalState Sets the enum emotional state of this mood.
     */
    public void setEmotionalState(EmotionalState emotionalState) {
        this.emotionalState = emotionalState;
    }

    /**
     * @return The value of this mood.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value Set the integer value of this mood.
     */
    public void setValue(int value) {
        this.value = value;
    }
}
