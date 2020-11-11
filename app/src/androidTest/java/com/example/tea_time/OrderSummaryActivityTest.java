package com.example.tea_time;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.ContentView;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.app.Instrumentation.ActivityResult;
import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.tea_time.OrderActivity.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class OrderSummaryActivityTest {
    private static final String VALID_PHONE_NUMBER = "123-345-6789";
    private static final String emailMessage = "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!";
    //ActivityScenarioRule

    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule = new IntentsTestRule<>(OrderSummaryActivity.class);


    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

//    @Before
//    public void grandPermissions(){
//        //Intended for Android M+, ensures permission to use is granted before running the Test.
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            getInstrumentation().getUiAutomation().executeShellCommand("pm grant "+getTargetContext().getPackageName()+" android.permission.CALL_PHONE");
//        }
//    }


    @Test
    public void clickSendEmailButton_SendsEmail() {
        onView(withId(R.id.send_email_button)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_TEXT, emailMessage)));

    }
}
