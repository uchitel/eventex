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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import dev.uchitel.eventex.UIEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSender extends Fragment implements View.OnClickListener {
    private ViewGroup fragView;

    public FragmentSender() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = (ViewGroup) inflater.inflate(R.layout.fragment_sender, container, false);
        return fragView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.view_btn).setOnClickListener(this);
        view.findViewById(R.id.viewgroup_btn).setOnClickListener(this);
        view.findViewById(R.id.fragment_btn).setOnClickListener(this);
        view.findViewById(R.id.activity_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String text = button.getText().toString();
        switch (v.getId()) {
            case R.id.view_btn:
                new UIEvent(MsgIds.MSG_WITH_TEXT).setText(text).post(v);
                break;
            case R.id.viewgroup_btn:
                new UIEvent(MsgIds.MSG_WITH_TEXT).setText(text).post(fragView);
                break;
            case R.id.fragment_btn:
                new UIEvent(MsgIds.MSG_WITH_TEXT).setText(text).post(this);
                break;
            case R.id.activity_btn:
                new UIEvent(MsgIds.MSG_WITH_TEXT).setText(text).post(getActivity());
                break;
        }
    }
}
