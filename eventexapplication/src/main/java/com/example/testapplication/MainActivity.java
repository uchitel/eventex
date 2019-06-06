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

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, UIEventListener {
    private static final String TAG = "MainActivity";

    private int current = R.id.button1;
    @Nullable
    private ViewGroup frame1 = null;
    @Nullable
    private ViewGroup frame2 = null;
    @Nullable
    private TextView toastText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            current = savedInstanceState.getInt("current");
        }
        setContentView(R.layout.activity_main);
        frame1 = findViewById(R.id.frame1);
        frame2 = findViewById(R.id.frame2);
        toastText = findViewById(R.id.toastView);

        prepareLayout(current, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        cleanupView();
        prepareLayout(v.getId(), null);
    }

    private void prepareLayout(int screen, @Nullable Bundle bundle) {
        current = screen;
        switch (screen) {
            case R.id.button1:
                createLayout1(bundle);
                break;
            case R.id.button2:
                createLayout2();
                break;
            case R.id.button3:
                createLayout3(bundle);
                break;
            case R.id.button4:
                createLayout4();
                break;
            case R.id.button5:
                createLayout5();
                break;
        }
        showToast(getString(R.string.empty));

    }

    private void createLayout1(@Nullable Bundle bundle) {
        if (frame2 == null || frame1 == null)
            return;

        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new FragmentReceiver(), "fragment1Tag").commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame2, new FragmentSender(), "fragment2Tag").commit();
        }
        setTitle("Four Buttons");
    }

    private void createLayout2() {
        LayoutParent receiver = new LayoutParent(this);
        updateLayout(frame1, receiver);


        RVPostMessage rvLayout = new RVPostMessage(this);
        updateLayout(frame2, rvLayout);
        setTitle("Layouts Post");
    }

    private void createLayout3(@Nullable Bundle bundle) {
        if (frame2 == null || frame1 == null)
            return;

        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new FragmentParent(), "fragment1Tag").commit();
        }

        RVPostMessage rvLayout = new RVPostMessage(this);
        updateLayout(frame2, rvLayout);
        setTitle("Fragments Post");
    }

    private void createLayout4() {
        LayoutParent reciever = new LayoutParent(this);
        updateLayout(frame1, reciever);


        RVSendMessage rvLayout = new RVSendMessage(this);
        updateLayout(frame2, rvLayout);
        setTitle("Layouts Send");
    }

    private void createLayout5() {
        CustomEventRecipient topLayout = new CustomEventRecipient(this);
        updateLayout(frame1, topLayout);

        ComplexObjectSender bottomLayout = new ComplexObjectSender(this);
        updateLayout(frame2, bottomLayout);
        setTitle("Custom Data");
    }

    private void updateLayout(@Nullable ViewGroup parent, @Nullable View view) {
        if (parent == null || view == null)
            return;
        if (parent.getChildCount() > 0)
            return;

        parent.addView(view);
    }

    private void cleanupView() {
        if (frame2 == null || frame1 == null)
            return;

        Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("fragment1Tag");
        if (fragment1 != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
        }
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag("fragment2Tag");
        if (fragment2 != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
        }
        frame2.removeAllViews();
        frame1.removeAllViews();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", current);
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        Log.d(TAG, "onMessage() " + uiEvent);
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_TO_ACTIVITY:
                showToast("Activity " + uiEvent.getText());
                return true;
            // should not happen
            case MsgIds.MSG_TO_GRANDCHILD:
            case MsgIds.MSG_TO_PARENT:
            case MsgIds.MSG_TO_CHILD:
            case MsgIds.MSG_TO_GRANDCHILD_INTERCEPT:
            case MsgIds.MSG_TO_ACTIVITY_INTERCEPT:
            case MsgIds.MSG_TO_PARENT_INTERCEPT:
            case MsgIds.MSG_TO_CHILD_INTERCEPT:
                showToast("Error: " + uiEvent.getText());
        }
        return false;
    }

    @Override
    public boolean onMessageIntercept(@NonNull UIEvent uiEvent) {
        Log.d(TAG, "onMessageIntercept() " + uiEvent);
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_TO_ACTIVITY_INTERCEPT:
                showToast("Activity " + uiEvent.getText());
                return true;
        }
        return false;
    }

    private void showToast(String text) {
        if (toastText != null)
            toastText.setText(text);
    }
}
