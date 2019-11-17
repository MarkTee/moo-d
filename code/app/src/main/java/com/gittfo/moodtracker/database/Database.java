package com.gittfo.moodtracker.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.util.Consumer;

import com.gittfo.moodtracker.mood.MoodEvent;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;

/**
 * A singleton class for abstracting out firestore
 */
public class Database {
    private static final String TAG = "DB_INTERNAL";
    public static final String PREFS = "databasesharedprefereneces";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static String username;

    private String userId;

    /**
     * Create a new database object and store the userID of the current user
     *
     * @param c Context in which the database is created
     */
    private Database (Context c) {
        userId = c.getSharedPreferences(Database.PREFS, MODE_PRIVATE)
                .getString("user", "");
    }

    /**
     * Get an instance of the database
     *
     * @param c Context in which the database is obtained
     * @return An instance of the database object
     */
    public static Database get(Context c) {
        return new Database(c);
    }

    /**
     * Return an instance of the Database object with testymctestface301's
     * user ID.
     *
     * @param c context
     * @return instance of Database
     */
    public static Database getMock(Context c){
        Database mocked  = new Database(c);
        mocked.userId = "105648403813593449833";
        return mocked;
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

    /**
     * Returns the userID of the current user (each user has a unique userID).
     *
     * @return The userID of the current user
     */
    public String currentUser() {
        return userId;
    }

    /**
     * Updates a user's MoodEvent from the database. This is necessary when the user chooses to edit
     * a MoodEvent, making changes to its data. The database knows the proper MoodEvent to update
     * based on the MoodEvent's ID.
     *
     * @param me The MoodEvent to be updated
     */
    public void updateMoodEvent(MoodEvent me) {
        Log.d(TAG, "UPDATING TO: " + me.toString());
        db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document(me.getId())
                .set(me);
    }

    /**
     * Retrieves a MoodEvent from the database based on its ID.
     *
     * @param isEdit The ID of the MoodEvent to be retrieved
     * @return A Task containing the specified MoodEvent
     */
    public Task<MoodEvent> getMoodByID(String isEdit) {
        DocumentReference moods = db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document(isEdit);
        Task<DocumentSnapshot> t = moods.get();
        return t.continueWithTask(task -> {
            DocumentSnapshot ds = task.getResult();
            return Tasks.call(() -> MoodEvent.getMoodEventFromFirebase(ds));
        });
    }

    /**
     * Permanently deletes a user's MoodEvent from the database. The database knows the proper
     * MoodEvent to delete based on the MoodEvent's ID.
     *
     * @param moode The MoodEvent to be deleted
     */
    public void deleteMoodEvent(MoodEvent moode) {
        db.collection("users")
                .document(currentUser())
                .collection("moods")
                .document(moode.getId())
                .delete();
    }

    public void setUserName(String username) {
        db.collection("users")
                .document(currentUser())
                .update("username", username);
        Database.username = username;
    }


    /**
     * Gets the username syncronasly, returning null if the information is not yet available
     * @return The username if available, or null
     */
    public String getUserName() {
       return getUserName(null);
    }

    /**
     * Returns the username syncronously
     * Or null if the username is not yet queried
     *
     * @param callback an optional callback to be called when the data is available
     * @return The username, or null if the username is not yet available
     */
    public String getUserName(Consumer<String> callback) {
        db.collection("users")
                .document(currentUser())
                .get().addOnSuccessListener(documentSnapshot -> {
                    String s = documentSnapshot.getString("username");
                    if (s != null) {
                        Database.username = s;
                    }
                    if (callback != null) {
                        callback.accept(s);
                    }
                });

        return username;
    }


    /**
     * Uploads an image to firebase, callback for success result
     * @param bitmap the bitmap to upload
     * @param callback the success status
     * @return the string at which the image can be found
     */
    public String uploadImage(Bitmap bitmap, Consumer<Boolean> callback) {
        String name = UUID.randomUUID().toString();
        StorageReference imageRef = storage.getReference().child(name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(exception -> {
            if (callback != null)
                callback.accept(true);
        }).addOnSuccessListener(taskSnapshot -> {
            if (callback != null)
                callback.accept(false);
        });

        return name;
    }

    /**
     * Gets the image from firebase
     * @param loc The storage URL to get from
     * @param callback a function that has the image in Bitmap form, or null if it could not get the image
     */
    public void downloadImage(String loc, Consumer<Bitmap> callback) {
        StorageReference imageRef = storage.getReference().child(loc);

        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            if (callback != null)
                callback.accept(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        }).addOnFailureListener(exception -> {
            if (callback != null)
                callback.accept(null);
        });
    }

    /**
     * Initializes data in the database
    */
    public void init() {
        getUserName();
    }
}