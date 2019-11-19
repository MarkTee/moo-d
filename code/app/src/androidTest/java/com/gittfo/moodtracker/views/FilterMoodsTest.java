package com.gittfo.moodtracker.views;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


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


    public void checkMood(String mood, int position){
        onView(
                allOf(TestUtil.getElementFromMatchAtPosition(allOf(
                        withId(R.id.user_mood_textView),
                        withText(mood),
                        isDisplayed()), position))
        );
    }

    public void checkAllMoods(){
        for (int i=0; i<6; i+=1){
            checkAngry(i);
        }

        for (int i=6; i<11; i+=1){
            checkDisgusted(i);
        }

        for (int i=11; i<15; i+=1){
            checkSurprised(i);
        }

        for(int i=15; i<18; i+=1){
            checkAfraid(i);
        }

        for(int i=18; i<20; i+=1){
            checkSad(i);
        }

        checkHappy(21);

    }

    public void checkHappy(int position){
        checkMood("HAPPY", position);
    }

    public void checkSad(int position){
        checkMood("SAD", position);
    }

    public void checkAfraid(int position){
        checkMood("AFRAID", position);
    }

    public void checkSurprised(int position){
        checkMood("SURPRISED", position);
    }

    public void checkDisgusted(int position){
        checkMood("DISGUSTED", position);
    }

    public void checkAngry(int position){
        checkMood("ANGRY", position);
    }

    @AfterSuite
    public void deleteMood(){
        TestUtil.deleteMood();
    }


}
