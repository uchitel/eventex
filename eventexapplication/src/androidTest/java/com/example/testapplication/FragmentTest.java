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
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FragmentTest extends TestUtils {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fragmentTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button3), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.parent_edit_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("empty")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.child_edit_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("empty")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.grand_child_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText3.check(matches(withText("empty")));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.parent_edit_text), withText("empty"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withText("Mercury"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatCheckedTextView.perform(click());

//        checkToast(mActivityTestRule.getActivity(), "Activity Mercury");

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText4.check(matches(withText("Mercury")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.child_edit_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText5.check(matches(withText("Mercury")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mercury"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText6.check(matches(withText("Mercury")));

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withText("Venus"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText7.check(matches(withText("Venus")));

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.child_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText8.check(matches(withText("Venus")));

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.grand_child_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText9.check(matches(withText("Venus")));

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withText("Earth"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText10.check(matches(withText("Venus")));

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText11.check(matches(withText("Earth")));

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.grand_child_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText12.check(matches(withText("Earth")));

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withText("Mars"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Venus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText13.check(matches(withText("Venus")));

        ViewInteraction editText14 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText14.check(matches(withText("Earth")));

        ViewInteraction editText15 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText15.check(matches(withText("Mars")));

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withText("Jupiter"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction editText16 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Jupiter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText16.check(matches(withText("Jupiter")));

        ViewInteraction editText17 = onView(
                allOf(withId(R.id.child_edit_text), withText("Earth"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText17.check(matches(withText("Earth")));

        ViewInteraction editText18 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText18.check(matches(withText("Mars")));

        ViewInteraction appCompatCheckedTextView6 = onView(
                allOf(withText("Saturn"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatCheckedTextView6.perform(click());

        ViewInteraction editText19 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText19.check(matches(withText("Saturn")));

        ViewInteraction editText20 = onView(
                allOf(withId(R.id.child_edit_text), withText("Saturn"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText20.check(matches(withText("Saturn")));

        ViewInteraction editText21 = onView(
                allOf(withId(R.id.grand_child_text), withText("Mars"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText21.check(matches(withText("Mars")));

        onView(withId(R.id.recycler_view_id)).perform(RecyclerViewActions.scrollToPosition(6));

        ViewInteraction appCompatCheckedTextView7 = onView(
                allOf(withText("Uranus"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatCheckedTextView7.perform(click());

        ViewInteraction editText22 = onView(
                allOf(withId(R.id.parent_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame1),
                                        0),
                                1),
                        isDisplayed()));
        editText22.check(matches(withText("Uranus")));

        ViewInteraction editText23 = onView(
                allOf(withId(R.id.child_edit_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_child),
                                        0),
                                1),
                        isDisplayed()));
        editText23.check(matches(withText("Uranus")));

        ViewInteraction editText24 = onView(
                allOf(withId(R.id.grand_child_text), withText("Uranus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.grand_child_cont),
                                        0),
                                1),
                        isDisplayed()));
        editText24.check(matches(withText("Uranus")));

        ViewInteraction appCompatCheckedTextView8 = onView(
                allOf(withText("Neptune"),
                        childAtPosition(
                                allOf(withId(R.id.recycler_view_id),
                                        childAtPosition(
                                                withClassName(is("com.example.testapplication.RVLayout")),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatCheckedTextView8.perform(click());

//        checkToast(mActivityTestRule.getActivity(), "Activity Neptune");
    }
}
