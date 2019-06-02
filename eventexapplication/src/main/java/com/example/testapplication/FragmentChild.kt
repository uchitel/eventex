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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import dev.uchitel.eventex.UIEvent
import dev.uchitel.eventex.UIEventListener

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentChild : Fragment(), UIEventListener {
    private var editText: EditText? = null

    override fun onMessage(uiEvent: UIEvent): Boolean {
        if (!uiEvent.isAppNamespace()) {
            return false
        }

        when (uiEvent.what) {
            MsgIds.MSG_TO_CHILD -> {
                setText(uiEvent.text)
                return true
            }
            MsgIds.MSG_TO_PARENT,
            MsgIds.MSG_TO_ACTIVITY -> {
                setText(uiEvent.text)
                return false
            }
            // should not happen
            MsgIds.MSG_TO_GRANDCHILD,
            MsgIds.MSG_TO_GRANDCHILD_INTERCEPT,
            MsgIds.MSG_TO_ACTIVITY_INTERCEPT,
            MsgIds.MSG_TO_PARENT_INTERCEPT,
            MsgIds.MSG_TO_CHILD_INTERCEPT -> {
                setText("error")
                return false
            }
        }
        return false
    }

    override fun onMessageIntercept(uiEvent: UIEvent): Boolean {
        if (!uiEvent.isAppNamespace()) {
            return false
        }

        when (uiEvent.what) {
            MsgIds.MSG_TO_CHILD_INTERCEPT -> {
                setText(uiEvent.text)
                return true
            }
            MsgIds.MSG_TO_GRANDCHILD_INTERCEPT -> {
                setText(uiEvent.text)
                return false
            }
            MsgIds.MSG_TO_ACTIVITY_INTERCEPT,
            MsgIds.MSG_TO_PARENT_INTERCEPT -> {
                setText("error")
                return false
            }
        }
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.layout_child, container, false)
        editText = v.findViewById(R.id.child_edit_text)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = FragmentGrandchild()
            childFragmentManager
                    .beginTransaction()
                    .replace(R.id.grand_child_cont, fragment)
                    .commit()
        }
    }

    private fun setText(text: String) {
        editText?.setText(text)
    }
}
