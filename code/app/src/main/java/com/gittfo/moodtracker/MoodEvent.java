package com.gittfo.moodtracker;

import java.util.Date;

/**
 * A 'Mood Event' is a mood, together with a bunch of relevant metadata, like a location.
 * The creation and display of MoodEvents is the core functionality of the mood tracker app.
 */
public class MoodEvent {

    // Brief textual comment about this event
    private String comment;
    // Location of an event, stored as a string for now. Type might change later
    private String location;
    // The name of a photograph corresponding to this event
    private String photoReference;
    // Brief textual comment describing the reason for this event
    private String reason;
    // The time this event happened
    private Date date;
    // The number of people around during this event
    private int SocialSituation;
    // Last but not least, the actual Mood associated to this event
    private Mood mood;

    /**
     * Create a new mood a event, which is a mood, together with metadata.
     * @param comment Brief comment on this event.
     * @param location Where the event happened.
     * @param photoReference Filename for a photo of the event.
     * @param reason Reason for this event, i.e. "breakup".
     * @param date When this event happened.
     * @param socialSituation How many people were around.
     * @param mood The mood of this event.
     */
    public MoodEvent(String comment, String location, String photoReference, String reason, Date date, int socialSituation, Mood mood) {
        this.comment = comment;
        this.location = location;
        this.photoReference = photoReference;
        this.reason = reason;
        this.date = date;
        SocialSituation = socialSituation;
        this.mood = mood;
    }

    /**
     * @return A description of this event.
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment The description of this event.
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * @return When this event happened.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date When this event happened.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return The number of people around during this event.
     */
    public int getSocialSituation() {
        return SocialSituation;
    }

    /**
     * @param socialSituation The number of people around during this event.
     */
    public void setSocialSituation(int socialSituation) {
        SocialSituation = socialSituation;
    }

    /**
     * @return The mood of this event.
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * @param mood The mood of this event.
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
