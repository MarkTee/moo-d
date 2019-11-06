package com.gittfo.moodtracker;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddMoodEventActivityTest {
    private Solo solo;

    // Create a rule for the AddMoodEventActivity
    @Rule
    public ActivityTestRule<AddMoodEventActivity> rule =
            new ActivityTestRule<>(AddMoodEventActivity.class, true, true);

    /**
     * Runs before all tests and creates a solo instance
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    /**
     * Ensure we can get the appropriate activity
     *
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    // After testing is complete, ensure we've closed all opened activities
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
