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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import dev.uchitel.eventex.UIEvent;

public class RVLayout extends FrameLayout {
    RecyclerView recyclerView;

    public RVLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public RVLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RVLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_rv, this);
        int orientation = getResources().getConfiguration().orientation;
        int spanCount = 3;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 2;
        }
        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setAdapter(new RvAdapter(getContext()));
    }

    /*

     */
    private static class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {
        String[] planetList;

        RvAdapter(Context context) {
            planetList = context.getResources().getStringArray(R.array.planets);
        }

        @NonNull
        @Override
        public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item, parent, false);
            RvViewHolder holder = new RvViewHolder(v);
            v.setOnClickListener(v1 -> {
                RvViewHolder holder1 = (RvViewHolder) v1.getTag();
                String text = holder1.text.getText().toString();
                switch (text) {
                    case "Mercury":
                        new UIEvent(MsgIds.MSG_TO_ACTIVITY).setText(text).post(v1);
                        break;
                    case "Venus":
                        new UIEvent(MsgIds.MSG_TO_PARENT).setText(text).post(v1);
                        break;
                    case "Earth":
                        new UIEvent(MsgIds.MSG_TO_CHILD).setText(text).post(v1);
                        break;
                    case "Mars":
                        new UIEvent(MsgIds.MSG_TO_GRANDCHILD).setText(text).post(v1);
                        break;
                    case "Jupiter":
                        new UIEvent(MsgIds.MSG_TO_PARENT_INTERCEPT).setText(text).post(v1);
                        break;
                    case "Saturn":
                        new UIEvent(MsgIds.MSG_TO_CHILD_INTERCEPT).setText(text).post(v1);
                        break;
                    case "Uranus":
                        new UIEvent(MsgIds.MSG_TO_GRANDCHILD_INTERCEPT).setText(text).post(v1);
                        break;
                    case "Neptune":
                        new UIEvent(MsgIds.MSG_TO_ACTIVITY_INTERCEPT).setText(text).post(v1);
                        break;
                    default:
                        Log.d("RVLayout", "unprocessed message " + text);
                        break;
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
            holder.onBindViewHolder(planetList[position]);
        }

        @Override
        public int getItemCount() {
            return planetList.length;
        }

        static class RvViewHolder extends RecyclerView.ViewHolder {
            TextView text;
            View view;

            RvViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                text = itemView.findViewById(android.R.id.text1);
            }

            void onBindViewHolder(String string) {
                view.setTag(this);
                text.setText(string);
            }
        }
    }
}
