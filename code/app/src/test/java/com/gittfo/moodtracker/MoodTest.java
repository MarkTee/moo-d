package com.gittfo.moodtracker;

import com.gittfo.moodtracker.mood.Mood;

import org.junit.Test;

import static com.gittfo.moodtracker.mood.Mood.emotionalStateFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the Mood class.
 *
 * The class is quite simplistic, but these tests have been added for the sake
 * of completeness.
 *
 * The tests mainly ensure that the proper pre-defined mood data is returned when starting from a
 * string representation.
 */
public class MoodTest {

    // Test the "HAPPY" mood
    @Test
    public void testHappy() {
        String moodString = "HAPPY";

        // Given a string, obtain the correct emotional state
        Mood.EmotionalState emotionalState = emotionalStateFromString(moodString);
        assertEquals(emotionalState, Mood.EmotionalState.HAPPY);

        // Given an emotional state, obtain the correct default mood
        Mood mood = Mood.moodFromEmotionalState(emotionalState);
        assertEquals(mood, Mood.DEFAULT_HAPPY);
    }
}
