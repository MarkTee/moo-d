package com.gittfo.moodtracker.views;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.POWER_SERVICE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMoodTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, true) {

        @Override
        protected void beforeActivityLaunched() {
            InstrumentationRegistry.getInstrumentation().getTargetContext().getSharedPreferences(Database.PREFS, MODE_PRIVATE)
                    .edit()
                    .putString("user", "105648403813593449833")
                    .apply();
        }
    };

    @Before
    public void clickPlusButton(){
        onView(withId(R.id.fab)).perform(click());
    }

//    @Before
//    public void deleteExistingMoods(){
//        while(true){
//            try{
//                deleteMood();
//            } catch (Exception e){
//                return;
//            }
//        }
//    }
    
    @Test
    public void addMoodTest() {

        saveMoodEvent();

        pressOkOnDialog();

        selectHappy();

        selectSad();

        selectAfraid();

        selectSurprised();

        selectDisgusted();

        selectAngry();

        saveMoodEvent();

        // TODO check position in RecyclerView
        // https://developer.android.com/reference/android/support/test/espresso/contrib/RecyclerViewActions
       onView(allOf(withId(R.id.user_mood_textView), withText("ANGRY"), isDisplayed()));
    }

    @Test
    public void addMoodWithCommentTest() {

        selectHappy();

        enterComment("Test Comment");

        saveMoodEvent();

        // TODO check position in RecyclerView
        // https://developer.android.com/reference/android/support/test/espresso/contrib/RecyclerViewActions
        onView(allOf(withId(R.id.user_mood_textView), withText("HAPPY"), isDisplayed()));
        onView(allOf(withId(R.id.user_reason_textView), withText("Test Comment"), isDisplayed()));
    }

    @Test
    public void addMoodWithZeroPeople() {

        selectSad();

        // click the other buttons
        onView(allOf(withId(R.id.social_button_zero), withText("0"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.social_button_one), withText("1"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.social_button_two_plus), withText("2+"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.social_button_crowd), withText("A CROWD"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.social_button_na), withText("N/A"))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.social_button_zero), withText("0"))).perform(scrollTo(), click());

        saveMoodEvent();

        // TODO check position in RecyclerView
        // https://developer.android.com/reference/android/support/test/espresso/contrib/RecyclerViewActions
        onView(allOf(withId(R.id.num_people_textView), withText("zero"), isDisplayed()));
        onView(allOf(withId(R.id.user_mood_textView), withText("SAD"), isDisplayed()));
    }

    // TODO
//    @Test
//    public void addMoodWithPhotoTest(){
//        selectAfraid();
//        // https://developer.android.com/training/testing/espresso/intents
//
//    }

    @Test
    public void addMoodWithLocation() {

        selectSurprised();

        onView(allOf(
                withId(R.id.location_button),
                withText("Use Location (optional)"))
        ).perform(scrollTo(), click());

        saveMoodEvent();

        // TODO check position in RecyclerView
        // https://developer.android.com/reference/android/support/test/espresso/contrib/RecyclerViewActions
        onView(allOf(withId(R.id.user_mood_textView), withText("SURPRISED"), isDisplayed()));
        onView(allOf(withId(R.id.location_textView), withText(not("NaN, NaN")), isDisplayed()));
    }

    @After
    public void deleteMood() {
        onView(allOf(
                withId(R.id.delete_button),
                withContentDescription("delete button"),
                isDisplayed())
        ).perform(click());

        pressOkOnDialog();
    }

    public void saveMoodEvent(){
        onView(allOf(
                withId(R.id.save_mood_event_button),
                withText("Save Mood Event"))
        ).perform(scrollTo(), click());
    }

    public void pressOkOnDialog(){
        onView(allOf(
                withId(android.R.id.button1),
                withText("OK"))
        ).perform(scrollTo(), click());
    }

    public void selectMood(int id, String text){
        onView(allOf(
                withId(id),
                withText(text))
        ).perform(scrollTo(), click());
    }
    public void selectHappy(){
        selectMood(R.id.happy_mood_button, "HAPPY");
    }

    public void selectSad(){
      selectMood(R.id.sad_mood_button, "SAD");
    }

    public void selectAfraid(){
        selectMood(R.id.afraid_mood_button, "AFRAID");
    }

    public void selectSurprised(){
        selectMood(R.id.surprised_mood_button, "SURPRISED");
    }

    public void selectDisgusted(){
        selectMood(R.id.disgusted_mood_button, "DISGUSTED");
    }

    public void selectAngry(){
        selectMood(R.id.angry_mood_button, "ANGRY");
    }

    public void enterComment(String text){
        onView(allOf(withId(R.id.reason_entry)))
                .perform(scrollTo(), replaceText(text), closeSoftKeyboard());
    }
}
