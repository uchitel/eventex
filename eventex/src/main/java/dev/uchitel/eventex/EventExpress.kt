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

@file:JvmName("EventExpress")

package dev.uchitel.eventex

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.annotation.AnyThread
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.ViewGroup
import android.content.ContextWrapper
import android.util.Log

@AnyThread
internal fun Activity.postMessage(uiEvent: UIEvent) {
    Handler(Looper.getMainLooper()).post {
        if (!sendMessage(uiEvent)) {
            Log.w("EventEx", "unprocessed $uiEvent")
        }
    }
}

@UiThread
internal fun Fragment.postMessage(message: UIEvent) {
    assert(activity != null) { "Fragment must be attached to activity to post UIEvent." }
    activity?.postMessage(message)
}

@UiThread
internal fun View.postMessage(uiEvent: UIEvent) {
    val activity: Activity? = getActivity(context)
    assert(activity != null) { "View must be attached to view hierarchy to post UIEvent." }
    activity?.postMessage(uiEvent)
}

@UiThread
private fun Activity.sendMessage(uiEvent: UIEvent): Boolean {
    (this as? UIEventListener)?.run {
        if (onMessageIntercept(uiEvent))
            return true
    }

    (this as? FragmentActivity)?.run {
        if (propagateFragmentMessage(uiEvent))
            return true
    }

    if (propagateUiEvent(uiEvent))
        return true

    (this as? UIEventListener)?.run {
        if (onMessage(uiEvent))
            return true
    }

    return false
}

@UiThread
private fun Activity.propagateUiEvent(uiEvent: UIEvent): Boolean {
    getRootGroup()?.run {
        return propagateUiEvent(uiEvent)
    }

    return false
}

@UiThread
private fun FragmentActivity.propagateFragmentMessage(uiEvent: UIEvent): Boolean {
    val fragManager = supportFragmentManager

    fragManager
            .fragments
            .forEach {
                if (propagateNestedFragments(it, uiEvent))
                    return true
            }

    return false
}

@UiThread
private fun propagateNestedFragments(frag: Fragment, uiEvent: UIEvent): Boolean {
    (frag as? UIEventListener)?.run {
        if (onMessageIntercept(uiEvent))
            return true
    }

    frag.childFragmentManager
            .fragments
            .forEach {
                if (propagateNestedFragments(it, uiEvent))
                    return true
            }

    (frag as? UIEventListener)?.run {
        if (onMessage(uiEvent))
            return true
    }

    return false
}

@UiThread
private fun ViewGroup.propagateUiEvent(uiEvent: UIEvent): Boolean {
    (this as? UIEventListener)?.run {
        if (onMessageIntercept(uiEvent))
            return true
    }

    (0 until childCount).mapNotNull { getChildAt(it) as? ViewGroup }.forEach {
        if (it.propagateUiEvent(uiEvent))
            return true
    }

    (this as? UIEventListener)?.run {
        if (onMessage(uiEvent))
            return true
    }

    return false
}

@UiThread
private fun Activity.getRootGroup(): ViewGroup? = window.decorView.rootView as? ViewGroup

// ContextWrapper
private fun getActivity(contextWrapper: ContextWrapper?): Activity? {
    var context: Context? = contextWrapper
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

private fun getActivity(context: Context?): Activity? {
    return if (context is ContextWrapper) {
        getActivity(context as? ContextWrapper)
    } else {
        (context as? Activity?)
    }
}
