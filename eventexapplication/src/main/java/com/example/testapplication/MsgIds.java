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

/**
 * Set of unique strings to identify UIEvent messages
 */
class MsgIds {
    static final String MSG_WITH_TEXT = "msg with text";
    static final String MSG_TO_ACTIVITY = "to activity";
    static final String MSG_TO_PARENT = "to parent";
    static final String MSG_TO_CHILD = "to child";
    static final String MSG_TO_GRANDCHILD = "to grandchild";
    static final String MSG_TO_ACTIVITY_INTERCEPT = "to activity intercept";
    static final String MSG_TO_PARENT_INTERCEPT = "to parent intercept";
    static final String MSG_TO_CHILD_INTERCEPT = "to child intercept";
    static final String MSG_TO_GRANDCHILD_INTERCEPT = "to grandchild intercept";

    static final String MSG_CUSTOM_DATA = "custom data";
}
