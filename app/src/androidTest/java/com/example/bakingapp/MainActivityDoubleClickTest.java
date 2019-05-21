package com.example.bakingapp;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bakingapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityDoubleClickTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

//    @Before
//    public void registerIdlingResource() {
//        IdlingRegistry.getInstance().register(activityTestRule.getActivity().getResources());
//    }

    @Test
    public void OnRecipeDoubleClick_LaunchDetailActivity() throws InterruptedException {
        //onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //android.support.test.espresso.Espresso.onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(1,ViewActions.click()));

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(1, androidx.test.espresso.action.ViewActions.click()));

        Thread.sleep(3000);
        android.support.test.espresso.Espresso.onView(withId(R.id.rv))
                .perform(android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.doubleClick()));


    }
}
