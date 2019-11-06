package com.gittfo.moodtracker.mood;


import android.graphics.Color;

/**
 * Represents the notion of a 'Mood' for our mood tracker app. Stores both information related to
 * the state of a mood (i.e. emotion) and the representation of a mood (i.e. color, emoticon).
 */
public class Mood {

    /**
     * The list of possible emotional states a user can choose for their moods.
     * Based on the "six universal moods" as classified by Paul Ekman.
     */
    public enum EmotionalState {
        HAPPY,
        SAD,
        ANGRY,
        DISGUSTED,
        AFRAID,
        SURPRISED,
    };

    /**
     * Given a string representation, return a valid EmotionalState
     *
     * @param emotionalState A string representation of a valid EmotionalState
     * @return               A valid EmotionalState
     */
    public static EmotionalState emotionalStateFromString(String emotionalState) {
        switch (emotionalState) {
            case "HAPPY": return EmotionalState.HAPPY;
            case "SAD": return EmotionalState.SAD;
            case "ANGRY": return EmotionalState.ANGRY;
            case "AFRAID": return EmotionalState.AFRAID;
            case "DISGUSTED": return EmotionalState.DISGUSTED;
            case "SURPRISED": return EmotionalState.SURPRISED;
        }
        return null;
    }

    /**
     * Return a string representation of a valid EmotionalState
     *
     * @param emotionalState A valid EmotionalState
     * @return               A string representation of a valid EmotionalState
     */
    public static Mood moodFromEmotionalState(EmotionalState emotionalState) {
        switch (emotionalState) {
            case HAPPY: return DEFAULT_HAPPY;
            case SAD: return DEFAULT_SAD;
            case ANGRY: return DEFAULT_ANGRY;
            case AFRAID: return DEFAULT_AFRAID;
            case DISGUSTED: return DEFAULT_DISGUSTED;
            case SURPRISED: return DEFAULT_SURPRISED;
        }
        return null;
    }

    // Filename of emoticon representing this mood. Will save on memory by not storing a copy
    private String emoticon;
    // Enum holding the "emotional state" this mood represents
    private EmotionalState emotionalState;
    // A String containing the name of the mood
    private String name;
    // A color for the mood, stored as an int
    private int color;

    /**
     * Creates a new mood
     *
     * @param emoticon       Filename of the icon representing this mood.
     * @param emotionalState Enum describing the emotional state of this mood
     * @param name           A String containing the name of the mood
     * @param color          A color for the mood
     */
    public Mood(String emoticon, EmotionalState emotionalState, String name, int color) {
        this.emoticon = emoticon;
        this.emotionalState = emotionalState;
        this.name = name;
        this.color = color;
    }

    /**
     * @return The filename for the icon representing this mood.
     */
    public String getEmoticon() {
        return emoticon;
    }

    /**
     * @return An enum describing the emotional state of this mood.
     */
    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    /**
     * @return A string containing the name of hte mood
     */
    public String getName() {
        return name;
    }

    /**
     * Color is the android in for the color codes
     * @return the color
     */
    public int getColor() {
        return this.color;
    }

    public static Mood DEFAULT_HAPPY = new Mood("@drawable/ic_sentiment_very_happy_black_32dp", Mood.EmotionalState.HAPPY,
            "Happy", Color.parseColor("#81c784"));
    public static Mood DEFAULT_SAD = new Mood("@drawable/ic_sentiment_sad_black_32dp", EmotionalState.SAD,
            "Sad", Color.parseColor("#64b5f6"));
    public static Mood DEFAULT_SURPRISED = new Mood("@drawable/ic_sentiment_satisfied_happy_32dp", EmotionalState.SURPRISED,
            "Surprised", Color.parseColor("#fff176"));
    public static Mood DEFAULT_AFRAID = new Mood("@drawable/ic_sentiment_sick_black_32dp", EmotionalState.AFRAID,
            "Afraid", Color.parseColor("#ffb74d"));
    public static Mood DEFAULT_DISGUSTED = new Mood("@drawable/ic_sentiment_neutral_black_32dp", EmotionalState.DISGUSTED,
            "Disgusted", Color.parseColor("#b39ddb"));
    public static Mood DEFAULT_ANGRY = new Mood("@drawable/ic_sentiment_sick_black_32dp", EmotionalState.ANGRY,
            "Angry", Color.parseColor("#ff8a65"));
}
