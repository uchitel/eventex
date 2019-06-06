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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SendToLayoutTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void layoutsSendTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button4), withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Layouts Send"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Layouts Send")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.parent_edit_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("empty")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.child_edit_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("empty")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.grand_child_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText3.check(matches(withText("empty")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.toastView), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("empty")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText4.check(matches(withText("Mercury")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.child_edit_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText5.check(matches(withText("Mercury")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText6.check(matches(withText("Mercury")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText7.check(matches(withText("Venus")));

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.child_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText8.check(matches(withText("Venus")));

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.grand_child_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText9.check(matches(withText("Venus")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText10.check(matches(withText("Venus")));

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText11.check(matches(withText("Earth")));

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.grand_child_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText12.check(matches(withText("Earth")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText13.check(matches(withText("Venus")));

        ViewInteraction editText14 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText14.check(matches(withText("Earth")));

        ViewInteraction editText15 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText15.check(matches(withText("Mars")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView5.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction editText16 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Jupiter"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText16.check(matches(withText("Jupiter")));

        ViewInteraction editText17 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText17.check(matches(withText("Earth")));

        ViewInteraction editText18 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText18.check(matches(withText("Mars")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView6.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction editText19 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText19.check(matches(withText("Saturn")));

        ViewInteraction editText20 = onView(
                allOf(withId(R.id.child_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText20.check(matches(withText("Saturn")));

        ViewInteraction editText21 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText21.check(matches(withText("Mars")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView7.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction editText22 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText22.check(matches(withText("Uranus")));

        ViewInteraction editText23 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText23.check(matches(withText("Uranus")));

        ViewInteraction editText24 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText24.check(matches(withText("Uranus")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Activity Mercury")));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView8.perform(actionOnItemAtPosition(7, click()));

        ViewInteraction editText25 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText25.check(matches(withText("Uranus")));

        ViewInteraction editText26 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText26.check(matches(withText("Uranus")));

        ViewInteraction editText27 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText27.check(matches(withText("Uranus")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.toastView), withText("Activity Neptune"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("Activity Neptune")));

        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.recycler_view_id),
                        childAtPosition(
                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                0)));
        recyclerView9.perform(actionOnItemAtPosition(8, click()));

        ViewInteraction editText28 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText28.check(matches(withText("Uranus")));

        ViewInteraction editText29 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText29.check(matches(withText("Uranus")));

        ViewInteraction editText30 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText30.check(matches(withText("Uranus")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.toastView), withText("Activity Neptune"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView11.check(matches(withText("Activity Neptune")));
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
