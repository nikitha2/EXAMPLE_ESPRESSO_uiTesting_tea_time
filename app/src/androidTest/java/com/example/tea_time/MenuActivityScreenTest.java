package com.example.tea_time;

import android.content.Context;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {

    @Rule
    public ActivityScenarioRule<MenuActivity> mActivityTestRule= new ActivityScenarioRule<>(MenuActivity.class);
    private IdlingResource mIdlingResource;
    @Before
    public void registerIdlingResource() {
        mActivityTestRule.getScenario().onActivity(activity -> {
            mIdlingResource=activity.getIdlingResource();
            IdlingRegistry.getInstance().register(mIdlingResource);
        });

    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
    //clicking on a gridView tea item and checking that it opens up the corresponding tea OrderActivity.
    @Test
    public void clickGreenTeaGrid_opensOrderActivityForGreenTea(){
        String green_tea_name = getApplicationContext().getResources().getString(R.string.green_tea_name) ;

        onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).
                onChildView(withId(R.id.tea_grid_name)).perform(click());

        //onView(withText(green_tea_name)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tea_name_text_view),withText(green_tea_name))).check(matches(isDisplayed()));
    }


    @Test
    public void changeImage_sameActivity() {
        onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).
                check(matches(isDisplayed()));

    }



}
