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

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SendToLayoutTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sendToLayoutTest() {
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

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Mercury"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatCheckedTextView.perform(click());

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

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Venus"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatCheckedTextView2.perform(click());

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

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Earth"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatCheckedTextView3.perform(click());

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
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText11.check(matches(withText("Venus")));

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText12.check(matches(withText("Earth")));

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.grand_child_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText13.check(matches(withText("Earth")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("Activity Mercury")));

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Mars"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction editText14 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText14.check(matches(withText("Venus")));

        ViewInteraction editText15 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText15.check(matches(withText("Earth")));

        ViewInteraction editText16 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText16.check(matches(withText("Mars")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Activity Mercury")));

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withId(android.R.id.text1), withText("Jupiter"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction editText17 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Jupiter"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText17.check(matches(withText("Jupiter")));

        ViewInteraction editText18 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText18.check(matches(withText("Earth")));

        ViewInteraction editText19 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText19.check(matches(withText("Mars")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Activity Mercury")));

        ViewInteraction appCompatCheckedTextView6 = onView(
                allOf(withId(android.R.id.text1), withText("Saturn"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatCheckedTextView6.perform(click());

        ViewInteraction editText20 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText20.check(matches(withText("Saturn")));

        ViewInteraction editText21 = onView(
                allOf(withId(R.id.child_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText21.check(matches(withText("Saturn")));

        ViewInteraction editText22 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText22.check(matches(withText("Mars")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Activity Mercury")));

        ViewInteraction appCompatCheckedTextView7 = onView(
                allOf(withId(android.R.id.text1), withText("Uranus"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatCheckedTextView7.perform(click());

        ViewInteraction editText23 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText23.check(matches(withText("Uranus")));

        ViewInteraction editText24 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText24.check(matches(withText("Uranus")));

        ViewInteraction editText25 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText25.check(matches(withText("Uranus")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.toastView), withText("Activity Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Activity Mercury")));

        ViewInteraction appCompatCheckedTextView8 = onView(
                allOf(withId(android.R.id.text1), withText("Neptune"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVSendMessage")),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatCheckedTextView8.perform(click());

        ViewInteraction editText26 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText26.check(matches(withText("Uranus")));

        ViewInteraction editText27 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText27.check(matches(withText("Uranus")));

        ViewInteraction editText28 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText28.check(matches(withText("Uranus")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.toastView), withText("Activity Neptune"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("Activity Neptune")));
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
