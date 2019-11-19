package com.gittfo.moodtracker.views;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

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
        // yes, there are multiple better ways to do this

        TestUtil.createHappy();

        TestUtil.createSad();
        TestUtil.createSad();

        TestUtil.createAfraid();
        TestUtil.createAfraid();
        TestUtil.createAfraid();

        TestUtil.createSurprised();
        TestUtil.createSurprised();
        TestUtil.createSurprised();
        TestUtil.createSurprised();

        TestUtil.createDisgusted();
        TestUtil.createDisgusted();
        TestUtil.createDisgusted();
        TestUtil.createDisgusted();
        TestUtil.createDisgusted();

        TestUtil.createAngry();
        TestUtil.createAngry();
        TestUtil.createAngry();
        TestUtil.createAngry();
        TestUtil.createAngry();
        TestUtil.createAngry();

    }

    @Test
    public void all(){
        checkAllMoods();
    }




    public void checkAllMoods(){
        for (int i=0; i<6; i+=1){
            TestUtil.checkAngry(i);
        }

        for (int i=6; i<11; i+=1){
            TestUtil.checkDisgusted(i);
        }

        for (int i=11; i<15; i+=1){
            TestUtil.checkSurprised(i);
        }

        for(int i=15; i<18; i+=1){
            TestUtil.checkAfraid(i);
        }

        for(int i=18; i<20; i+=1){
            TestUtil.checkSad(i);
        }

        TestUtil.checkHappy(21);

    }

    @AfterClass
    public static void deleteMoods(){
        for (int i=0; i<21; i+=1){
            TestUtil.deleteMoodAtPosition(i);
        }
    }


}
