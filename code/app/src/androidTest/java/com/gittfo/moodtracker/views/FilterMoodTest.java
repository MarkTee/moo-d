package com.gittfo.moodtracker.views;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gittfo.moodtracker.database.Database;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterMoodTest {

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

    @Ignore
    @Test
    public void filterMoodTest() {

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                0)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton4.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                3)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction floatingActionButton5 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton5.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                3)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton10.perform(scrollTo(), click());

        ViewInteraction floatingActionButton6 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton6.perform(click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                3)));
        materialButton11.perform(scrollTo(), click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton12.perform(scrollTo(), click());

        ViewInteraction floatingActionButton7 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton7.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.surprised_mood_button), withText("SURPRISED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                2)));
        materialButton13.perform(scrollTo(), click());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton14.perform(scrollTo(), click());

        ViewInteraction floatingActionButton8 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton8.perform(click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(R.id.disgusted_mood_button), withText("DISGUSTED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                4)));
        materialButton15.perform(scrollTo(), click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton16.perform(scrollTo(), click());

        ViewInteraction floatingActionButton9 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton9.perform(click());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.disgusted_mood_button), withText("DISGUSTED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                4)));
        materialButton17.perform(scrollTo(), click());

        ViewInteraction materialButton18 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton18.perform(scrollTo(), click());

        ViewInteraction floatingActionButton10 = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton10.perform(click());

        ViewInteraction materialButton19 = onView(
                allOf(withId(R.id.angry_mood_button), withText("ANGRY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                5)));
        materialButton19.perform(scrollTo(), click());

        ViewInteraction materialButton20 = onView(
                allOf(withId(R.id.save_mood_event_button), withText("Save Mood Event"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_mood_root),
                                        0),
                                14)));
        materialButton20.perform(scrollTo(), click());

        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton21.perform(click());

        ViewInteraction materialButton22 = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton22.perform(click());

        ViewInteraction materialButton23 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton23.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.user_mood_textView), withText("HAPPY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(withText("HAPPY")));

        ViewInteraction materialButton24 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton24.perform(click());

        ViewInteraction materialButton25 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton25.perform(click());

        ViewInteraction materialButton26 = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton26.perform(click());

        ViewInteraction materialButton27 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton27.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText("SAD")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView3.check(matches(withText("SAD")));

        ViewInteraction materialButton28 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton28.perform(click());

        ViewInteraction materialButton29 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton29.perform(click());

        ViewInteraction materialButton30 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton30.perform(click());

        ViewInteraction materialButton31 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton31.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText("AFRAID")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("AFRAID")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView6.check(matches(withText("AFRAID")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView7.check(matches(withText("AFRAID")));

        ViewInteraction materialButton32 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton32.perform(click());

        ViewInteraction materialButton33 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton33.perform(click());

        ViewInteraction materialButton34 = onView(
                allOf(withId(R.id.surprised_mood_button), withText("SURPRISED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton34.perform(click());

        ViewInteraction materialButton35 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton35.perform(click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SURPRISED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView8.check(matches(withText("SURPRISED")));

        ViewInteraction materialButton36 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton36.perform(click());

        ViewInteraction materialButton37 = onView(
                allOf(withId(R.id.surprised_mood_button), withText("SURPRISED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton37.perform(click());

        ViewInteraction materialButton38 = onView(
                allOf(withId(R.id.disgusted_mood_button), withText("DISGUSTED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton38.perform(click());

        ViewInteraction materialButton39 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton39.perform(click());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView9.check(matches(withText("DISGUSTED")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView10.check(matches(withText("DISGUSTED")));

        ViewInteraction materialButton40 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton40.perform(click());

        ViewInteraction materialButton41 = onView(
                allOf(withId(R.id.disgusted_mood_button), withText("DISGUSTED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton41.perform(click());

        ViewInteraction materialButton42 = onView(
                allOf(withId(R.id.angry_mood_button), withText("ANGRY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        materialButton42.perform(click());

        ViewInteraction materialButton43 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton43.perform(click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.user_mood_textView), withText("ANGRY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView11.check(matches(withText("ANGRY")));

        ViewInteraction materialButton44 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton44.perform(click());

        ViewInteraction materialButton45 = onView(
                allOf(withId(R.id.angry_mood_button), withText("ANGRY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        materialButton45.perform(click());

        ViewInteraction materialButton46 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton46.perform(click());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.user_mood_textView), withText("ANGRY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView12.check(matches(withText("ANGRY")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView13.check(matches(withText("DISGUSTED")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView14.check(matches(withText("DISGUSTED")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SURPRISED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView15.check(matches(withText("SURPRISED")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView16.check(matches(withText("AFRAID")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView17.check(matches(withText("AFRAID")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView18.check(matches(withText("AFRAID")));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView19.check(matches(withText("SAD")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView20.check(matches(withText("SAD")));

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.user_mood_textView), withText("HAPPY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView21.check(matches(withText("HAPPY")));

        ViewInteraction materialButton47 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton47.perform(click());

        ViewInteraction materialButton48 = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton48.perform(click());

        ViewInteraction materialButton49 = onView(
                allOf(withId(R.id.afraid_mood_button), withText("AFRAID"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton49.perform(click());

        ViewInteraction materialButton50 = onView(
                allOf(withId(R.id.disgusted_mood_button), withText("DISGUSTED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton50.perform(click());

        ViewInteraction materialButton51 = onView(
                allOf(withId(R.id.angry_mood_button), withText("ANGRY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        materialButton51.perform(click());

        ViewInteraction materialButton52 = onView(
                allOf(withId(R.id.surprised_mood_button), withText("SURPRISED"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton52.perform(click());

        ViewInteraction materialButton53 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton53.perform(click());

        ViewInteraction materialButton54 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton54.perform(click());

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.user_mood_textView), withText("HAPPY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView22.check(matches(withText("HAPPY")));

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView23.check(matches(withText("SAD")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView24.check(matches(withText("SAD")));

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView25.check(matches(withText("AFRAID")));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView26.check(matches(withText("AFRAID")));

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.user_mood_textView), withText("AFRAID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView27.check(matches(withText("AFRAID")));

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SURPRISED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView28.check(matches(withText("SURPRISED")));

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView29.check(matches(withText("DISGUSTED")));

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.user_mood_textView), withText("DISGUSTED"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView30.check(matches(withText("DISGUSTED")));

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.user_mood_textView), withText("ANGRY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView31.check(matches(withText("ANGRY")));

        ViewInteraction materialButton55 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton55.perform(click());

        ViewInteraction materialButton56 = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton56.perform(click());

        ViewInteraction materialButton57 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton57.perform(click());

        ViewInteraction materialButton58 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton58.perform(click());

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView32.check(matches(withText("SAD")));

        ViewInteraction textView33 = onView(
                allOf(withId(R.id.user_mood_textView), withText("SAD"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView33.check(matches(withText("SAD")));

        ViewInteraction textView34 = onView(
                allOf(withId(R.id.user_mood_textView), withText("HAPPY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView34.check(matches(withText("HAPPY")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton59 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton59.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction materialButton60 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton60.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction materialButton61 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton61.perform(scrollTo(), click());

        ViewInteraction materialButton62 = onView(
                allOf(withId(R.id.toolbar_filter_button), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profile_toolbar),
                                        0),
                                0),
                        isDisplayed()));
        materialButton62.perform(click());

        ViewInteraction materialButton63 = onView(
                allOf(withId(R.id.happy_mood_button), withText("HAPPY"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton63.perform(click());

        ViewInteraction materialButton64 = onView(
                allOf(withId(R.id.sad_mood_button), withText("SAD"),
                        childAtPosition(
                                allOf(withId(R.id.mood_selection_buttons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton64.perform(click());

        ViewInteraction materialButton65 = onView(
                allOf(withText("Apply Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        materialButton65.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction materialButton66 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton66.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction materialButton67 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton67.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction materialButton68 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton68.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction materialButton69 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton69.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction materialButton70 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton70.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction materialButton71 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton71.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton10 = onView(
                allOf(withId(R.id.delete_button), withContentDescription("delete button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton10.perform(click());

        ViewInteraction materialButton72 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton72.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
