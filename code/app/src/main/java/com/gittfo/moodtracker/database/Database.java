package com.gittfo.moodtracker.database;

import android.content.Context;
import android.util.Log;


import com.gittfo.moodtracker.mood.MoodEvent;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Thread.sleep;

/**
 * A singleton class for abstracting out firestore
 */
public class Database {
    private static final String TAG = "DB_INTERNAL";
    public static final String PREFS = "databasesharedprefereneces";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private String userId;

    private Database (Context c) {
        userId = c.getSharedPreferences(Database.PREFS, MODE_PRIVATE)
                .getString("user", "");
    }

    public static Database get(Context c) {
        return new Database(c);
    }

    /**
     * Gets all the mood events for the signed in user
     *
     * Usage:
     *
     * <pre>
     * <code>
     *   // Print out all mood events of current user
     *   Database.get(this).getMoods()
     *       .addOnSuccessListener(moods -> {
     *          for(MoodEvent ev : moods) {
     *              System.out.println(ev.toString());
     *          }
     *      });
     * </code>
     * </pre>
     *
     * @return A task containing a list of mood events for the signed in user
     */
    public Task<List<MoodEvent>> getMoods() {
        CollectionReference moods = db.collection("users")
                .document(currentUser())
                .collection("moods");
        return moods.get().continueWithTask(
                task -> {
                    Log.d(TAG, "Found a mood");
                    List<MoodEvent> me = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MoodEvent v = MoodEvent.getMoodEventFromFirebase(document);
                        if (v != null) {
                            me.add(v);
                        }
                    }
                    return Tasks.call(() -> me);
                }
        );
    }

    /**
     * Adds a new Mood Event to firebase for the current user
     *
     * Usage:
     * <pre><code>
     *  Database.get(this).addMoodEvent(new MoodEvent(
     *      new Mood("sad", "dsfjajsdj"),
     *      new Date()
     *  ));
     *  </code></pre>
     * @param me the MoodEvent
     */
    public void addMoodEvent(MoodEvent me) {
        db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document()
                .set(me);
    }

    public String currentUser() {
        return userId;
    }

    public void updateMoodEvent(MoodEvent me) {
        db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document(me.getId())
                .set(me);
    }

    public MoodEvent getMoodByID(String isEdit) {
        DocumentReference moods = db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document(isEdit);
        Task<DocumentSnapshot> t = moods.get();
        // Im so sorry
        while (!t.isComplete()) {
            try {
                sleep(100); // TODO: remove this all
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return MoodEvent.getMoodEventFromFirebase(t.getResult());
    }
}