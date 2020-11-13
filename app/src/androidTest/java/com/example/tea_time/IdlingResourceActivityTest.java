package com.example.tea_time;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.tea_time.IdlingResource.ImageDownloader;


/**
 * Same as Espresso's BasicSample, but with an Idling Resource to help with synchronization.
 */
// one way of keeping track of idilresources
@RunWith(AndroidJUnit4.class)
@LargeTest
public class IdlingResourceActivityTest {

    private IdlingResource mIdlingResource;

    /**
     * Use {@link ActivityScenario to launch and get access to the activity.
     * {@link ActivityScenario#onActivity(ActivityScenario.ActivityAction)} provides a thread-safe
     * mechanism to access the activity.
     * //getIdlingResource() is called to create an instance of IdlingResource in MenuActivity-  This step is not required as instance is created here
     */
    /*@Before comes after Activity creation and therefore is probably not a good time to initialize all mocks.
    The Activity will always be launched before test code begins executing.

    In this scenario- we need an instance of IdlingResource to be created before we call
     ImageDownloader.downloadImage(this, MenuActivity.this, mIdlingResource); in MenuActivity.
     if we keep this line in onCreate/ onResume/ onState- which are executed before @Before, an instance will not be created.
     Therefore this line needs to be called after activity fully created and after @Before is executed.
    */
    @Rule
    public ActivityScenarioRule<MenuActivity> mActivityTestRule= new ActivityScenarioRule<>(MenuActivity.class);
    Context instrumentationContext ;
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

    @Test
    public void changeImage_sameActivity() {
        String green_tea_name = getApplicationContext().getResources().getString(R.string.green_tea_name) ;

        onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).
                check(matches(isDisplayed()));

    }


}
