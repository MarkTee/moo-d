package com.gittfo.moodtracker.views;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.testng.Assert.fail;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FollowTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, true) {

        @Override
        protected void beforeActivityLaunched() {
            InstrumentationRegistry.getInstrumentation().getTargetContext().getSharedPreferences(Database.PREFS, MODE_PRIVATE)
                    .edit()
                    .putString("user", "followmoodtest")
                    .apply();
        }
    };

    public void followMe() {
        try {
            // make testy follow us
            new URL("https://us-central1-moo-d-95679.cloudfunctions.net/followUser?uid=105648403813593449833&oid=followmoodtest")
                    .openConnection().getContent();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void unfollowMe() {
        try {
            // make testy unfollow us
            new URL("https://us-central1-moo-d-95679.cloudfunctions.net/unfollowUser?uid=105648403813593449833&oid=followmoodtest")
                    .openConnection().getContent();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void followUser() throws InterruptedException {
        onView(withId(R.id.timeline_menu_item)).perform(click());
        onView(withId(R.id.follow_button)).perform(click());
        onView(withId(R.id.user_follow_editText)).perform(replaceText("Testy"), closeSoftKeyboard());
        onView(withId(R.id.send_request_button)).perform(click());
        // TODO jacob will make a firebase function to assert that someone is following a user
    }

    @Test
    public void acceptFollowRequest() throws InterruptedException {
        followMe();
        onView(withId(R.id.inbox_menu_item)).perform(click());
        // wait for the api call
        Thread.sleep(1000);
        onView(withId(R.id.follow_request_username)).perform(click());
        onView(withId(R.id.allow_button)).perform(click());
        // TODO jacob will make a firebase function to assert that someone is following a user
        unfollowMe();
        onView(withId(R.id.timeline_menu_item)).perform(click());
    }

    @Test
    public void denyFollowRequest() throws InterruptedException {
        followMe();
        onView(withId(R.id.inbox_menu_item)).perform(click());
        // wait for the api call
        Thread.sleep(1000);
        onView(withId(R.id.follow_request_username)).perform(click());
        onView(withId(R.id.deny_button)).perform(click());
        // TODO jacob will make a firebase function to assert that someone is following a user;
    }

}

