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

package com.example.testapplication

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import dev.uchitel.eventex.UIEvent
import java.util.*

data class CustomUIEvent(val date: Date) : UIEvent(MsgIds.MSG_CUSTOM_DATA)

class ComplexObjectSender @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.layout_complex_sender, this)
        val buttonSend = findViewById<View>(R.id.button_send_2)
        buttonSend?.also {
            it.setOnClickListener {
                val title: EditText = findViewById(R.id.edit_text_title)
                val editNumber: EditText = findViewById(R.id.edit_text_number)

                CustomUIEvent(Calendar.getInstance().time)
                        .setText(title.text.toString() )
                        .setNumber(editNumber.text.toString().toInt())
                        .post(this)
            }
        }
    }
}
