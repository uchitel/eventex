/*
 * *****************************************************************************
 *   Copyright (C) 2019 Alexander Uchitel.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */

package com.example.testapplication;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FourButtonsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fourButtonsTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.view_btn), withText("From View"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.text_receiver_frag), withText("From View"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("From View")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.viewgroup_btn), withText("From ViewGroup"),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.text_receiver_frag), withText("From ViewGroup"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("From ViewGroup")));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.fragment_btn), withText("From Fragment"),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.text_receiver_frag), withText("From Fragment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText3.check(matches(withText("From Fragment")));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.activity_btn), withText("From Activity"),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.text_receiver_frag), withText("From Activity"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText4.check(matches(withText("From Activity")));
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
