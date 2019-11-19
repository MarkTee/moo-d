package com.gittfo.moodtracker.views;

import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class TestUtil {


    public static void saveMoodEvent(){
        onView(allOf(
                withId(R.id.save_mood_event_button),
                withText("Save Mood Event"))
        ).perform(scrollTo(), click());
    }

    public static void pressOkOnDialog(){
        onView(allOf(
                withId(android.R.id.button1),
                withText("OK"))
        ).perform(scrollTo(), click());
    }

    public static void selectMood(int id, String text){
        onView(allOf(
                withId(id),
                withText(text))
        ).perform(scrollTo(), click());
    }
    public static void selectHappy(){
        selectMood(R.id.happy_mood_button, "HAPPY");
    }

    public static void selectSad(){
        selectMood(R.id.sad_mood_button, "SAD");
    }

    public static void selectAfraid(){
        selectMood(R.id.afraid_mood_button, "AFRAID");
    }

    public static void selectSurprised(){
        selectMood(R.id.surprised_mood_button, "SURPRISED");
    }

    public static void selectDisgusted(){
        selectMood(R.id.disgusted_mood_button, "DISGUSTED");
    }

    public static void selectAngry(){
        selectMood(R.id.angry_mood_button, "ANGRY");
    }

    public static void enterComment(String text){
        onView(allOf(withId(R.id.reason_entry)))
                .perform(scrollTo(), replaceText(text), closeSoftKeyboard());
    }

    // https://stackoverflow.com/questions/32387137/espresso-match-first-element-found-when-many-are-in-hierarchy
    public static Matcher<View> getElementFromMatchAtPosition(final Matcher<View> matcher, final int position) {
        return new BaseMatcher<View>() {

            int counter = 0;
            @Override
            public boolean matches(final Object item) {
                if (matcher.matches(item)) {
                    if(counter == position) {
                        counter++;
                        return true;
                    }
                    counter++;
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Element at hierarchy position "+position);
            }
        };
    }

    public static void clickPlusButton(){
        onView(withId(R.id.fab)).perform(click());
    }

    public static void deleteExistingMoods(){
        while(true){
            try{
                deleteMood();
            } catch (Exception e){
                return;
            }
        }
    }

    public static void deleteMood() {
        onView(
                allOf(TestUtil.getElementFromMatchAtPosition(allOf(
                        withId(R.id.delete_button),
                        withContentDescription("delete button"),
                        isDisplayed()), 0))
        ).perform(click());

        TestUtil.pressOkOnDialog();

    }

    public static void deleteMoodAtPosition(int position) {
        onView(
                allOf(TestUtil.getElementFromMatchAtPosition(allOf(
                        withId(R.id.delete_button),
                        withContentDescription("delete button"),
                        isDisplayed()), position))
        ).perform(click());

        TestUtil.pressOkOnDialog();

    }

    public static void createHappy(){
        clickPlusButton();
        selectHappy();
        saveMoodEvent();
    }

    public static void createSad(){
        clickPlusButton();
        selectSad();
        saveMoodEvent();
    }

    public static void createAfraid(){
        clickPlusButton();
        selectAfraid();
        saveMoodEvent();
    }

    public static void createSurprised(){
        clickPlusButton();
        selectSurprised();
        saveMoodEvent();
    }

    public static void createDisgusted(){
        clickPlusButton();
        selectDisgusted();
        saveMoodEvent();
    }

    public static void createAngry(){
        clickPlusButton();
        selectAngry();
        saveMoodEvent();
    }

    public static void checkMood(String mood, int position){
        onView(
                allOf(TestUtil.getElementFromMatchAtPosition(allOf(
                        withId(R.id.user_mood_textView),
                        withText(mood),
                        isDisplayed()), position))
        );
    }

    public static void checkHappy(int position){
        checkMood("HAPPY", position);
    }

    public static void checkSad(int position){
        checkMood("SAD", position);
    }

    public static void checkAfraid(int position){
        checkMood("AFRAID", position);
    }

    public static void checkSurprised(int position){
        checkMood("SURPRISED", position);
    }

    public static void checkDisgusted(int position){
        checkMood("DISGUSTED", position);
    }

    public static void checkAngry(int position){
        checkMood("ANGRY", position);
    }

    public static void clickFilter(){
        onView(allOf(
                withId(R.id.toolbar_filter_button),
                withText("FILTER"))
        ).perform(scrollTo(), click());
    }
}
