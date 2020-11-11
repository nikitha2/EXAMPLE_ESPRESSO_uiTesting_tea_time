package com.example.tea_time;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class OrderActivityIntentTest {

    @Rule
    public IntentsTestRule<OrderActivity> mActivityRule = new IntentsTestRule<>(OrderActivity.class);


    @Test
    public void clickBrewTeaButton_opensOrderSummaryScreen() {
        String mQuantity="1";
        String mTotalPrice="$5.00";

        onView((withId(R.id.increment_button))).perform(click());
        onView(withId(R.id.quantity_text_view)).check(matches(withText(mQuantity)));
        onView(withId(R.id.cost_text_view)).check(matches(withText(mTotalPrice)));

        onView(withId(R.id.brew_tea_button)).perform(click());

        String mSize = getApplicationContext().getResources().getString(R.string.tea_size_small);
        String mMilkType = getApplicationContext().getResources().getString(R.string.milk_type_none);
        String mSugarType = getApplicationContext().getResources().getString(R.string.sweet_type_0);

        //intended(allOf(hasComponent(OrderSummaryActivity.class.getName())));

        intended(allOf(hasComponent(OrderSummaryActivity.class.getName())));
    }

}
