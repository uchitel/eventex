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

import android.util.Log;

import androidx.annotation.NonNull;

import org.robolectric.annotation.Implements;

@SuppressWarnings("unused")
@Implements(Log.class)
class ShadowLog {
    private static final StringBuilder buffer = new StringBuilder();

    public static int e(java.lang.String tag, java.lang.String msg) {
        append('e', tag, msg);
        return 0;
    }

    public static int w(java.lang.String tag, java.lang.String msg) {
        append('w', tag, msg);
        return 0;
    }

    public static int d(java.lang.String tag, java.lang.String msg) {
        append('d', tag, msg);
        return 0;
    }

    public static int i(java.lang.String tag, java.lang.String msg) {
        append('i', tag, msg);
        return 0;
    }

    public static int v(java.lang.String tag, java.lang.String msg) {
        append('v', tag, msg);
        return 0;
    }

    private static void append(char type, @NonNull String tag, @NonNull String msg) {
        if (buffer.length() > 0) {
            buffer.append('\n');
        }
        buffer.append(type)
                .append(' ')
                .append(tag)
                .append(' ')
                .append(msg);
    }

    static boolean find(String text) {
        return buffer.indexOf(text) != -1;
    }

    static void reset() {
        buffer.delete(0, buffer.length());
    }
}
