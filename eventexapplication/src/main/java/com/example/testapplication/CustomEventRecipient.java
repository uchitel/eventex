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

import androidx.annotation.NonNull;

import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;

import java.text.SimpleDateFormat;

public class CustomEventRecipient extends FrameLayout implements UIEventListener {
    public CustomEventRecipient(Context context) {
        super(context);
        init(context);
    }

    public CustomEventRecipient(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEventRecipient(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        inflate(context, R.layout.layout_complex_reciever, this);
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) {
            return false;
        }

        switch (uiEvent.what) {
            case MsgIds.MSG_CUSTOM_DATA:
                CustomUIEvent msg = (CustomUIEvent) uiEvent;
                String currentDate = SimpleDateFormat.getDateTimeInstance().format(msg.getDate());
                String text = "title: " + msg.getText()
                        + "\ndate: " + currentDate
                        + "\nnumber= " + msg.getNumber();
                EditText editText = findViewById(R.id.edit_text_receiver);
                editText.setText(text);
                return true;
        }
        return false;
    }
}
