package com.gittfo.moodtracker;

import com.gittfo.moodtracker.mood.MoodEvent;

import org.junit.jupiter.api.Test;

import static com.gittfo.moodtracker.mood.MoodEvent.socialSituationFromFirebaseString;
import static com.gittfo.moodtracker.mood.MoodEvent.socialSituationFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the MoodEvent class.
 *
 */
public class MoodEventTest {

    /**
     * Test socialSituationFromFirebaseString method, which is used to translate string
     * representations (as described in our database), to valid Social Situations
     */
    @Test
    public void testSocialSituationFromFirebaseString() {
        assertEquals(socialSituationFromFirebaseString("ZERO"), MoodEvent.SocialSituation.ZERO);
        assertEquals(socialSituationFromFirebaseString("ONE"), MoodEvent.SocialSituation.ONE);
        assertEquals(socialSituationFromFirebaseString("TWOPLUS"), MoodEvent.SocialSituation.TWOPLUS);
        assertEquals(socialSituationFromFirebaseString("CROWD"), MoodEvent.SocialSituation.CROWD);
        assertEquals(socialSituationFromFirebaseString("N/A"), MoodEvent.SocialSituation.NA);
        // test an undefined value
        assertEquals(socialSituationFromFirebaseString("test"), MoodEvent.SocialSituation.NA);
    }

    /**
     * Test socialSituationFromString method, which is used to translate string representations (as
     * displayed in our app's UI), to valid Social Situations
     */
    @Test
    public void testSocialSituationFromString() {
        assertEquals(socialSituationFromString("0"), MoodEvent.SocialSituation.ZERO);
        assertEquals(socialSituationFromString("1"), MoodEvent.SocialSituation.ONE);
        assertEquals(socialSituationFromString("2+"), MoodEvent.SocialSituation.TWOPLUS);
        assertEquals(socialSituationFromString("A Crowd"), MoodEvent.SocialSituation.CROWD);
        assertEquals(socialSituationFromString("N/A"), MoodEvent.SocialSituation.NA);
        // test an undefined value
        assertEquals(socialSituationFromString("test"), MoodEvent.SocialSituation.NA);
    }
}
