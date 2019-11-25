package com.gittfo.moodtracker;

import com.gittfo.moodtracker.mood.Mood;
import com.gittfo.moodtracker.mood.MoodEvent;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.*;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

import static com.gittfo.moodtracker.mood.MoodEvent.socialSituationFromFirebaseString;
import static com.gittfo.moodtracker.mood.MoodEvent.socialSituationFromString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for the MoodEvent class
 *
 * The class is quite simplistic, but these tests have been added for the sake
 * of completeness.
 */
public class MoodEventTest {

    private Date date;

    /**
     * Test socialSituationFromFirebaseString method, which is used to translate string
     * representations (as described in our database) to valid Social Situations
     */
    @Test
    public void testSocialSituationFromFirebaseString() {
        // test all valid values
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
     * displayed in our app's UI) to valid Social Situations
     */
    @Test
    public void testSocialSituationFromString() {
        // test all valid values
        assertEquals(socialSituationFromString("0"), MoodEvent.SocialSituation.ZERO);
        assertEquals(socialSituationFromString("1"), MoodEvent.SocialSituation.ONE);
        assertEquals(socialSituationFromString("2+"), MoodEvent.SocialSituation.TWOPLUS);
        assertEquals(socialSituationFromString("A Crowd"), MoodEvent.SocialSituation.CROWD);
        assertEquals(socialSituationFromString("N/A"), MoodEvent.SocialSituation.NA);
        // test an undefined value
        assertEquals(socialSituationFromString("test"), MoodEvent.SocialSituation.NA);
    }


    /**
     * Return a default constructed mood event,
     * also sets the date of the test suite
     * @return a constructed mood event
     */
    public MoodEvent defaultMoodEvent(){
        this.date = new Date();
        return new MoodEvent(
                "test_photo_ref",
                "test_reason",
                date,
                MoodEvent.SocialSituation.NA,
                null,
                1.1,
                2.2
        );
    }

    // Test MoodEvent's basic getters and setters
    @Test
    public void basicGettersAndSetters(){
        MoodEvent test = defaultMoodEvent();

        assertEquals(test.getDate(), date);


        // lat long
        assertEquals(test.getLatitude(), 1.1);
        test.setLatitude(3.3);
        assertEquals(test.getLatitude(), 3.3);
        assertEquals(test.getLongitude(), 2.2);
        test.setLongitude(4.4);
        assertEquals(test.getLongitude(), 4.4);

        // photo ref
        assertEquals(test.getPhotoReference(), "test_photo_ref");
        test.setPhotoReference("test photo ref 2");
        assertEquals(test.getPhotoReference(), "test photo ref 2");

        //mood
        assertNull(test.getMood());
        test.setMood(Mood.EmotionalState.ANGRY);
        assertEquals(test.getMood(), Mood.EmotionalState.ANGRY);

        // social situation
        assertEquals(test.getSocialSituation(), MoodEvent.SocialSituation.NA);
        test.setSocialSituation(MoodEvent.SocialSituation.CROWD);
        assertEquals(test.getSocialSituation(), MoodEvent.SocialSituation.CROWD);

        //date setters
        Date testDate2 = new Date();
        test.setDate(testDate2);
        assertEquals(test.getDate(), testDate2);

        // username
        assertNull(test.getUsername());
        test.setUsername("test_username");
        assertEquals(test.getUsername(), "test_username");

        // reason
        assertEquals(test.getReason(), "test_reason");
        test.setReason("test_reason2");
        assertEquals(test.getReason(), "test_reason2");
    }

    // test ID functions of mood events
    @Test
    public void testMoodEventIDs(){
        MoodEvent idTest = MoodEvent.fromId("testid");
        assertEquals(idTest.getId(), "testid");
        idTest.setId("testid2");
        assertEquals(idTest.getId(), "testid2");
    }

    // test to string methods for mood event
    @Test
    public void testToString(){
        MoodEvent test = defaultMoodEvent();
        test.setMood(Mood.EmotionalState.ANGRY);
        assertEquals(
                test.toStringFull(),
                String.format(
                        Locale.CANADA,
                        "%s: (%s, %s, %s)\nLoc: %f, %f\nID: %s\nPhoto: %s\nSocial: %s",
                        test.getId(),
                        test.getMood().toString(),
                        test.getDate(),
                        test.getReason(),
                        test.getLongitude(),
                        test.getLatitude(),
                        test.getId(),
                        test.getPhotoReference(),
                        test.getSocialSituation()
                        )
                );

        assertEquals(
                test.toString(),
                String.format(
                        "%s: (%s, %s, %s)",
                        test.getId(),
                        test.getMood().toString(),
                        test.getDate().toString(),
                        test.getReason()
                        )
                );

    }

    // test getMoodEventFromJson
    @Test
    public void jsonConverstionTest(){
        String j = "{\"id\":\"0xGEiOxsZ2E2QyQk6T97\",\"mood\":\"ANGRY\",\"socialSituation\":\"TWOPLUS\",\"latitude\":0,\"photoReference\":\"\",\"longitude\":0,\"reason\":\"\",\"date\":{\"_seconds\":1574617138,\"_nanoseconds\":643000000},\"owner\":{\"id\":\"107882252205171063391\",\"username\":\"colter\"}}";
        JsonElement jsonElement = new JsonParser().parse(j);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        MoodEvent parsed = MoodEvent.getMoodEventFromJson(jsonObject);

        MoodEvent expected = new MoodEvent(
                "",
                "",
                new Date( 1000 * (long) 1574617138 ),
                MoodEvent.SocialSituation.TWOPLUS,
                Mood.emotionalStateFromString("ANGRY"),
                0,
                0
        );
        expected.setId("0xGEiOxsZ2E2QyQk6T97");
        expected.setUsername("colter");
        assertEquals(parsed.toStringFull(), expected.toStringFull());
    }
}
