package com.example.tea_time;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {

    @Rule
    public ActivityScenarioRule<OrderActivity> mActivityTestRule= new ActivityScenarioRule<>(OrderActivity.class);

    @Test
    public void clickIncrementButton_changeQualityAndCost(){

        //find view and perform an action on view
        onView((withId(R.id.increment_button))).perform(click());

        //check if view does what you expect
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1")));
        onView(withId(R.id.cost_text_view)).check(matches(withText("$5.00")));

    }

    @Test
    public void clickIncrementButtonTwice_changeQualityAndCost(){

        //find view and perform an action on view
        onView((withId(R.id.increment_button))).perform(click(),click());

        //check if view does what you expect
        onView(withId(R.id.quantity_text_view)).check(matches(withText("2")));
        onView(withId(R.id.cost_text_view)).check(matches(withText("$10.00")));

    }

    @Test
    public void clickDecrementButton_nochangeQualityAndCost(){

        //find view and perform an action on view
        onView((withId(R.id.decrement_button))).perform(click());

        //check if view does what you expect
        onView(withId(R.id.quantity_text_view)).check(matches(withText("0")));
        onView(withId(R.id.cost_text_view)).check(matches(withText("$0.00")));

    }
}
