package com.gittfo.moodtracker.views;

import android.view.View;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;




@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterMoodsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, true) {

        @Override
        protected void beforeActivityLaunched() {
            InstrumentationRegistry.getInstrumentation().getTargetContext().getSharedPreferences(Database.PREFS, MODE_PRIVATE)
                    .edit()
                    .putString("user", "filtermoodstest")
                    .apply();
        }
    };

    @Before
    public void setUp(){
        TestUtil.deleteExistingMoods();

        // create 21 moods
    }


    @After
    public void deleteMood(){
        TestUtil.deleteMood();
    }


}
