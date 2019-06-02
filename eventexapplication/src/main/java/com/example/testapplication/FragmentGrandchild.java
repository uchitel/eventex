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
import androidx.fragment.app.Fragment;
import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGrandchild extends Fragment implements UIEventListener {
    private View fragmentView;

    public FragmentGrandchild() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.layout_grandchild, container, false);
        return fragmentView;
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_TO_GRANDCHILD:
                setText(uiEvent.getText());
                return true;
            case MsgIds.MSG_TO_ACTIVITY:
            case MsgIds.MSG_TO_PARENT:
            case MsgIds.MSG_TO_CHILD:
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
            case MsgIds.MSG_TO_GRANDCHILD_INTERCEPT:
                setText(uiEvent.getText());
                return true;
            // should not happen
            case MsgIds.MSG_TO_ACTIVITY_INTERCEPT:
            case MsgIds.MSG_TO_PARENT_INTERCEPT:
            case MsgIds.MSG_TO_CHILD_INTERCEPT:
                setText("error");
                return false;
        }
        return false;
    }

    private void setText(@NonNull String text) {
        EditText editText = fragmentView.findViewById(R.id.grand_child_text);
        editText.setText(text);
    }
}
