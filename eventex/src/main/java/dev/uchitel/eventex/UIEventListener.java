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

package dev.uchitel.eventex;

import android.support.annotation.NonNull;

/**
 * Classes that extend {@link android.view.ViewGroup ViewGroup}, {@link android.support.v4.app.Fragment Fragment}, or {@link android.app.Activity Activity}
 * can impleement UIEventListener interface to receive {@link dev.uchitel.eventex.UIEvent UIEvent} events.
 */
public interface UIEventListener {
    /**
     * Override this function to be notified of {@link dev.uchitel.eventex.UIEvent UIEvent} events.
     * Any class which extends Activity, Fragment, or ViewGroup can override function onMessage.
     * <p>
     * The system implements children-first order of notification.
     *
     * @param uiEvent UIEvent or its subclass
     * @return true if message was processed, false to continue message broadcast
     */
    boolean onMessage(@NonNull final UIEvent uiEvent);

    /**
     * Function onMessageIntercept is called for Activities, Fragments, ViewGroups and classes derived
     * from them.
     * <p>
     * Override this function only when some parent layout must intercept some message processed
     * by it's child and there is no way to modify child layout to achieve desired behaviour.
     *
     * @param uiEvent {@link UIEvent} or its subclass
     * @return true if message was processed, false to continue message broadcast
     */
    default boolean onMessageIntercept(@NonNull final UIEvent uiEvent) {
        return false;
    }
}
