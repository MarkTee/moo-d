package com.gittfo.moodtracker.mood;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;


/**
 * A 'Mood Event' is a mood, together with a bunch of relevant metadata, like a location.
 * The creation and display of MoodEvents is the core functionality of the mood tracker app.
 */
public class MoodEvent {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Valid social situations for a MoodEvent
    public enum SocialSituation {
        ZERO,
        ONE,
        TWOPLUS,
        CROWD,
        NA
    };

    /**
     * Given a string representation, return a valid SocialSituation
     * @param socialSituation A string representation of a valid SocialSituation
     * @return                A valid SocialSituation
     */
    public static SocialSituation socialSituationFromString(String socialSituation) {
        switch (socialSituation) {
            case "0": return SocialSituation.ZERO;
            case "1": return SocialSituation.ONE;
            case "2+": return SocialSituation.TWOPLUS;
            case "A Crowd": return SocialSituation.CROWD;
            default:
                return SocialSituation.NA;
        }
    }

    // Location of an event, stored as a string for now. Type might change later
    private String location;
    // The name of a photograph corresponding to this event
    private String photoReference;
    // Brief textual comment describing the reason for this event
    private String reason;
    // The date/time the mood event was create
    private Date dateTime;
    // The number of people around during this event
    private SocialSituation socialSituation;
    // Last but not least, the actual Mood associated to this event
    private Mood.EmotionalState mood;
    // ID used for firebase
    @Exclude
    private String id;

    /**
     * Create a new mood a event, which is a mood, together with metadata.
     * @param location        Where the event happened.
     * @param photoReference  Filename for a photo of the event.
     * @param reason          Reason for this mood event, e.g. "breakup".
     * @param dateTime        When the mood event was created
     * @param socialSituation How many people were around.
     * @param mood            The mood of this event.
     */
    public MoodEvent(String location, String photoReference, String reason, Date dateTime, SocialSituation socialSituation, Mood.EmotionalState mood) {
        this.location = location;
        this.photoReference = photoReference;
        this.reason = reason;
        this.dateTime = dateTime;
        this.socialSituation = socialSituation;
        this.mood = mood;
    }

    /**
     * Create a MoodEvent object based on data stored in Firebase.
     *
     * @param document A Firebase document containing data describing a MoodEvent
     * @return         A MoodEvent object based on data stored in Firebase
     */
    public static MoodEvent getMoodEventFromFirebase(QueryDocumentSnapshot document) {
        MoodEvent moodEvent = new MoodEvent(
                document.getString("location"),
                document.getString("photoReference"),
                document.getString("reason"),
                document.getDate("date"),
                socialSituationFromString(document.getString("socialSituation")),
                Mood.emotionalStateFromString(document.getString("mood"))
        );
        moodEvent.setId(document.getId());
        return moodEvent;
    }

    /**
     * @return Where this event happened.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location Where this event happened.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The filename of a photo for this event.
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     * @param photoReference The filename of a photo for this event.
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    /**
     * @return A description of the reason for this event.
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason A description of the reason for this event.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return When the mood event was created
     */
    public Date getDate() {
        return dateTime;
    }

    /**
     * @param date When the mood event was created
     */
    public void setDate(Date date) {
        this.dateTime = date;
    }

    /**
     * @return The number of people around during this event.
     */
    public SocialSituation getSocialSituation() {
        return socialSituation;
    }

    /**
     * @param socialSituation The number of people around during this event.
     */
    public void setSocialSituation(SocialSituation socialSituation) {
        this.socialSituation = socialSituation;
    }

    /**
     * @return The mood of this event.
     */
    public Mood.EmotionalState getMood() {
        return mood;
    }

    /**
     * @param mood The mood of this event.
     */
    public void setMood(Mood.EmotionalState mood) {
        this.mood = mood;
    }
}
