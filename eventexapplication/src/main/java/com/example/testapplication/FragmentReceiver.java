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
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.support.v4.app.Fragment;

import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;

/**
 *
 */
public class FragmentReceiver extends Fragment implements UIEventListener {
    private EditText editText;

    public FragmentReceiver() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receiver, container, false);
        editText = view.findViewById(R.id.text_reciever_frag);
        return view;
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what){
            case MsgIds.MSG_WITH_TEXT:
                editText.setText(uiEvent.getText());
                return true;
        }
        return false;
    }
}
