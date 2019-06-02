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

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;

public class LayoutParent extends FrameLayout implements UIEventListener {
    public LayoutParent(Context context) {
        super(context);
        init();
    }

    public LayoutParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_parent, this);
        LayoutChild child = new LayoutChild(getContext());
        ViewGroup viewGroup = findViewById(R.id.frame_child);
        viewGroup.addView(child);
        TextView textView = findViewById(R.id.parent_title_text);
        textView.setText(R.string.parent_layout_title);
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_TO_PARENT:
                setText(uiEvent.getText());
                return true;
            case MsgIds.MSG_TO_ACTIVITY:
            case MsgIds.MSG_TO_CHILD:
            case MsgIds.MSG_TO_GRANDCHILD:
                setText(uiEvent.getText());
                return false;
                // should not happen
            case MsgIds.MSG_TO_GRANDCHILD_INTERCEPT:
            case MsgIds.MSG_TO_ACTIVITY_INTERCEPT:
            case MsgIds.MSG_TO_PARENT_INTERCEPT:
            case MsgIds.MSG_TO_CHILD_INTERCEPT:
                setText("error");
                return false;
        }
        return false;
    }

    @Override
    public boolean onMessageIntercept(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_TO_PARENT_INTERCEPT:
                setText(uiEvent.getText());
                return true;
            case MsgIds.MSG_TO_CHILD_INTERCEPT:
            case MsgIds.MSG_TO_GRANDCHILD_INTERCEPT:
                setText(uiEvent.getText());
                return false;
            // should not happen
            case MsgIds.MSG_TO_ACTIVITY_INTERCEPT:
                setText("error");
                return false;
        }
        return false;
    }

    private void setText(@NonNull String text) {
        EditText editText = findViewById(R.id.parent_edit_text);
        editText.setText(text);
    }
}
