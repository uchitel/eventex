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

package dev.uchitel.eventex

import android.app.Activity
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.annotation.AnyThread
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment

/**
 * Defines a message that can be delivered to any UI component within single activity.
 * Any UI class that derives from [ViewGroup](https://developer.android.com/reference/android/view/ViewGroup),
 * [Fragment](https://developer.android.com/guide/components/fragments),
 * or [Activity](https://developer.android.com/reference/android/app/Activity)
 * can receive this message by implementing interface [UiEventListener]
 *
 * The message is uniquely identified by properties [code] and [what]
 */
@Suppress("unused", "WeakerAccess")
open class UiEvent : Parcelable {

    /**
     * String value to identify this message. Value 'what' should be unique across application.
     * Empty string means property 'what' is not set and [code][code] is used as a unique identifier.
     */
    @JvmField
    val what: String

    /**
     * Integer value to identify this message. Value 'code' should be unique across application.
     * Value 0 means property 'code' is not set and [what] is used as a unique message identifier.
     */
    @JvmField
    val code: Int

    /**
     * The namespace is used to prevent conflicts between components written by different teams.
     *
     * The application should not assign any value to the namespace and leave it empty.
     * Android libraries on the other side should set namespace value to some unique string.
     * For example: 'library-name.company-name.com'
     * Library should always check if the namespace is matching library's namespace to prevent
     * conflicts with messages sent by application or other libraries.
     */
    var namespace: String = ""
        private set

    /**
     * Set message namespace. Application should not call this function and leave namespace empty string.
     * When Android .aar library sends some message it should set namespace to some unique string.
     * For example "library-name.company-name.com".
     * Library should always check if the namespace is matching library's namespace to prevent
     * conflicts with messages sent by application or other libraries.
     * This function has no effect if called after the message was [post].
     *
     * @param value Namespace for this message.
     */
    fun setNamespace(value: String): UiEvent = apply { if (!isSent()) namespace = value }

    /**
     * Call this function before processing message inside application code. See [namespace] how to
     * use namespace in Android libraries
     */
    fun isAppNamespace(): Boolean = namespace.isEmpty()

    /**
     * Optional text message to pass with this message to the recipient.
     */
    var text: String = ""
        private set

    /**
     * Set optional string value to pass with this message to the recipient.
     * Function does nothing if called after the message has been sent.
     */
    fun setText(value: String): UiEvent = apply { if (!isSent()) text = value }

    /**
     * Optional integer value to pass with this message.
     */
    var number: Int = 0
        private set

    /**
     * Set optional integer parameter to deliver with the message. Function does nothing if called
     * after the message has been sent.
     *
     * @param value
     */
    fun setNumber(value: Int): UiEvent = apply { if (!isSent()) number = value }

    /**
     *
     * @param code unique integer to identify this message.
     * @param what optional unique string to identify this message.
     * @constructor Creates an object of UiEvent.
     */
    @JvmOverloads
    constructor(code: Int, what: String = "") {
        assert(!(code == 0 && what.isEmpty())) { "Invalid paraemters compbination." }
        this.code = code
        this.what = what
    }

    /**
     * Create [UiEvent] with string identifier
     *
     * @param what unique string to identify this message.
     */
    constructor(what: String) : this(0, what)

    /**
     * Copy constructor
     */
    constructor(uiEvent: UiEvent) : this(uiEvent.code, uiEvent.what) {
        text = uiEvent.text
        number = uiEvent.number
        namespace = uiEvent.namespace
        sent = uiEvent.sent
    }

    /**
     * Asynchronously broadcast message to Activity, all Fragments, and UI components
     * of the current activity.
     * The message will not be sent if the [sentBy] view is deattached from the activity view.
     *
     * @param sentBy current UI component that is sending this message. Any class derived from
     * ViewGroup is a good candidate.
     */
    @UiThread
    fun post(sentBy: View) {
        sent = true
        sentBy.postMessage(this)
    }

    /**
     * Asynchronously broadcast message to Activity, all Fragments, and UI components
     * of the current activity.
     * The message will not be sent if the [sentBy] view is deattached from the activity view.
     *
     * @param sentBy current Fragment that is sending this message.
     */
    @UiThread
    fun post(sentBy: Fragment) {
        sent = true
        sentBy.postMessage(this)
    }

    /**
     * Asynchronously broadcast message to all Fragments and UI components of the current activity.
     *
     * @param sentBy current Activity.
     */
    @AnyThread
    fun post(sentBy: Activity) {
        sent = true
        sentBy.postMessage(this)
    }

    override fun toString(): String {
        return "UiEvent [code=$code, what='$what', namespace=\'$namespace\', number=$number, text=\'$text\']"
    }

    protected var sent: Boolean = false

    /**
     * Checks if message has already been sent.
     *
     * @return true if message has been sent, false otherwise.
     */
    fun isSent(): Boolean = sent

    // region equals and hashCode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UiEvent) return false

        if (code != other.code) return false
        if (number != other.number) return false
        if (sent != other.sent) return false
        if (what != other.what) return false
        if (namespace != other.namespace) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = what.hashCode()
        result = 31 * result + code
        result = 31 * result + namespace.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + number
        result = 31 * result + sent.hashCode()
        return result
    }
    // endregion

    // region Parcelable
    constructor(parcel: Parcel) {
        sent = parcel.readInt() == 1
        code = parcel.readInt()
        number = parcel.readInt()
        what = parcel.readString()?.toString() ?: ""
        text = parcel.readString()?.toString() ?: ""
        namespace = parcel.readString()?.toString() ?: ""
    }

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(if (sent) 1 else 0)
        parcel.writeInt(code)
        parcel.writeInt(number)
        parcel.writeString(what)
        parcel.writeString(text)
        parcel.writeString(namespace)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UiEvent> = object : Parcelable.Creator<UiEvent> {
            override fun createFromParcel(source: Parcel): UiEvent = UiEvent(source)
            override fun newArray(size: Int): Array<UiEvent?> = arrayOfNulls(size)
        }
    }
    // endregion
}
