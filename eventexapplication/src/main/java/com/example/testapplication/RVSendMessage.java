package com.example.testapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.uchitel.eventex.UIEvent;

class RVSendMessage extends FrameLayout {

    public RVSendMessage(@NonNull Context context) {
        super(context);
        init();
    }

    public RVSendMessage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RVSendMessage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        RecyclerView recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setAdapter(new RvAdapter(getContext()));
    }

    private static class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {
        final String[] planetList;

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
                        new UIEvent(MsgIds.MSG_TO_ACTIVITY).setText(text).send(v1);
                        break;
                    case "Venus":
                        new UIEvent(MsgIds.MSG_TO_PARENT).setText(text).send(v1);
                        break;
                    case "Earth":
                        new UIEvent(MsgIds.MSG_TO_CHILD).setText(text).send(v1);
                        break;
                    case "Mars":
                        new UIEvent(MsgIds.MSG_TO_GRANDCHILD).setText(text).send(v1);
                        break;
                    case "Jupiter":
                        new UIEvent(MsgIds.MSG_TO_PARENT_INTERCEPT).setText(text).send(v1);
                        break;
                    case "Saturn":
                        new UIEvent(MsgIds.MSG_TO_CHILD_INTERCEPT).setText(text).send(v1);
                        break;
                    case "Uranus":
                        new UIEvent(MsgIds.MSG_TO_GRANDCHILD_INTERCEPT).setText(text).send(v1);
                        break;
                    case "Neptune":
                        new UIEvent(MsgIds.MSG_TO_ACTIVITY_INTERCEPT).setText(text).send(v1);
                        break;
                    default:
                        Log.d("RVPostMessage", "unprocessed message " + text);
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
            final TextView text;
            final View view;

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
