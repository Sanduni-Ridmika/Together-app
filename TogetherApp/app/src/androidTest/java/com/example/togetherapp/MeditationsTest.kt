package com.example.togetherapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.togetherapp.Breathing
import com.example.togetherapp.Home
import com.example.togetherapp.Meditations
import com.example.togetherapp.Music
import com.example.togetherapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MeditationsTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(Meditations::class.java)

    @Test
    fun homeButtonClicked_startsHomeActivity() {
        // Perform the click on the home button
        onView(withId(R.id.homebutton)).perform(click())

        // Verify that Home activity is started
        onView(withId(R.id.home_activity_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun button1Clicked_startsBreathingActivity() {
        // Perform the click on button1
        onView(withId(R.id.button1)).perform(click())

        // Verify that Breathing activity is started
        onView(withId(R.id.breathing_activity_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun button2Clicked_startsMusicActivity() {
        // Perform the click on button2
        onView(withId(R.id.button2)).perform(click())

        // Verify that Music activity is started
        onView(withId(R.id.music_activity_layout)).check(matches(isDisplayed()))
    }
}
