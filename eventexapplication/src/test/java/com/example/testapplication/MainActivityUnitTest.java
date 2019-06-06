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
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import dev.uchitel.eventex.UIEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = ShadowLog.class)
public class MainActivityUnitTest {
    @Rule
    public final ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    private static final String TEXT = "some text";
    private MainActivity activity;

    @Before
    public void setUp() {
        activity = rule.getActivity();
    }

    @After
    public void tearDown() {
        ShadowLog.reset();
    }

    @Test
    public void unprocessedMessage_warningInLogCat() {
        View viewById = activity.findViewById(R.id.view_btn);
        assertNotNull(viewById);

        new UIEvent("invalid_msg").post(viewById);

        assertTrue(ShadowLog.find("w EventEx unprocessed UIEvent [code=0, what='invalid_msg', namespace='', number=0, text='']"));
    }

    @Test
    public void messageFromView_shouldBeProcessed() {
        View sender = activity.findViewById(R.id.view_btn);
        assertNotNull(sender);
        EditText recipient = activity.findViewById(R.id.text_receiver_frag);
        assertNotNull(recipient);
        assertNotEquals(TEXT, recipient.getText().toString());

        new UIEvent(MsgIds.MSG_WITH_TEXT).setText(TEXT).post(sender);

        assertEquals(TEXT, recipient.getText().toString());
        assertFalse(ShadowLog.find("EventEx unprocessed"));
    }

    @Test
    public void messageFromViewGroup_shouldBeProcessed() {
        ViewGroup sender = activity.findViewById(R.id.frame1);
        assertNotNull(sender);
        EditText recipient = activity.findViewById(R.id.text_receiver_frag);
        assertNotNull(recipient);
        assertNotEquals(TEXT, recipient.getText().toString());

        new UIEvent(MsgIds.MSG_WITH_TEXT).setText(TEXT).post(sender);

        assertEquals(TEXT, recipient.getText().toString());
        assertFalse(ShadowLog.find("EventEx unprocessed"));
    }

    @Test
    public void messageFromFragment_toItself_shouldBeProcessed() {
        List<Fragment> list = activity.getSupportFragmentManager().getFragments();
        Fragment fragment = list.get(0);
        assertNotNull(fragment);
        EditText recipient = activity.findViewById(R.id.text_receiver_frag);
        assertNotNull(recipient);
        assertNotEquals(TEXT, recipient.getText().toString());

        new UIEvent(MsgIds.MSG_WITH_TEXT).setText(TEXT).post(fragment);

        assertEquals(TEXT, recipient.getText().toString());
        assertFalse(ShadowLog.find("EventEx unprocessed"));
    }

    @Test
    public void messageFromFragment_toAnotherFragment_shouldBeProcessed() {
        List<Fragment> list = activity.getSupportFragmentManager().getFragments();
        Fragment fragment = list.get(1);
        assertNotNull(fragment);
        EditText recipient = activity.findViewById(R.id.text_receiver_frag);
        assertNotNull(recipient);
        assertNotEquals(TEXT, recipient.getText().toString());

        new UIEvent(MsgIds.MSG_WITH_TEXT).setText(TEXT).post(fragment);

        assertEquals(TEXT, recipient.getText().toString());
        assertFalse(ShadowLog.find("EventEx unprocessed"));
    }

    @Test
    public void messageFromActivity_toFragment_shouldBeProcessed() {
        EditText recipient = activity.findViewById(R.id.text_receiver_frag);
        assertNotNull(recipient);
        assertNotEquals(TEXT, recipient.getText().toString());

        new UIEvent(MsgIds.MSG_WITH_TEXT).setText(TEXT).post(activity);

        assertEquals(TEXT, recipient.getText().toString());
        assertFalse(ShadowLog.find("EventEx unprocessed"));
    }
}
