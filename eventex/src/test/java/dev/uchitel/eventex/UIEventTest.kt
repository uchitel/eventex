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
import android.content.Context
import android.os.Parcel
import android.view.View
import io.mockk.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UIEventTest {

    private var context: Context? = null
    private var activity: Activity? = null

    @Before
    fun setUp() {
        context = RuntimeEnvironment.systemContext
        activity = Robolectric.buildActivity(Activity::class.java).create().start().resume().get()
    }

    @Test
    fun constructor2Params_shouldSucceed() {
        val subject = UIEvent(123, "event_id")

        assertEquals(123, subject.code.toLong())
        assertEquals("event_id", subject.what)
        assertTrue(subject.isAppNamespace())
    }

    @Test(expected = AssertionError::class)
    fun constructor2Params_invalidValues_shouldAssert() {
        UIEvent(0, "")
    }

    @Test
    fun constructorString_shouldSucceed() {
        val subject = UIEvent("event_id")

        assertEquals(0, subject.code.toLong())
        assertEquals("event_id", subject.what)
        assertTrue(subject.isAppNamespace())
    }

    @Test(expected = AssertionError::class)
    fun constructorString_invalidValue_shouldAssert() {
        UIEvent("")
    }

    @Test
    fun constructorInt_shouldSucceed() {
        val subject = UIEvent(123)

        assertEquals(123, subject.code.toLong())
        assertEquals("", subject.what)
        assertTrue(subject.isAppNamespace())
    }

    @Test(expected = AssertionError::class)
    fun constructorInt_invalidValue_shouldAssert() {
        UIEvent(0)
    }

    @Test
    fun constructor_setAllValues_shouldSucceed() {
        val subject = UIEvent(1234, "event_id")
                .setText("text1")
                .setNamespace("eventex.uchitel.dev")
                .setNumber(987)

        assertEquals(1234, subject.code.toLong())
        assertEquals("event_id", subject.what)
        assertEquals("text1", subject.text)
        assertEquals("eventex.uchitel.dev", subject.namespace)
        assertEquals(987, subject.number.toLong())
        assertFalse(subject.isSent())
        assertFalse(subject.isAppNamespace())
    }

    @Test(expected = AssertionError::class)
    fun postByView_detachedFromActivity_shouldAssert() {
        val v = View(context)
        val subject = UIEvent(1234)
        assertFalse(subject.isSent())

        subject.post(v)
    }

    @Test
    fun postByView_shouldPostMessage() {
        val v = View(activity)
        activity?.setContentView(v)
        val subject = UIEvent(1234)
        assertFalse(subject.isSent())

        mockkStatic("dev.uchitel.eventex.EventExpress")
        spyk(v)

        subject.post(v)

        assertTrue(subject.isSent())
        verify { v.postMessage(eq(subject)) }
    }

    @Test
    fun postByActivity_shouldPostMessage() {
        val subject = UIEvent(1234)
        assertFalse(subject.isSent())

        spyk<Activity>()
        mockkStatic("dev.uchitel.eventex.EventExpress")

        activity?.also {
            subject.post(it)
        }

        assertTrue(subject.isSent())
        verify { activity?.postMessage(eq(subject)) }
    }

    @Test(expected = AssertionError::class)
    fun setText_afterPost_shouldAssert() {
        val v = View(context)
        val subject = UIEvent(1234)

        subject.post(v)

        subject.setText("text1")
    }

    @Test(expected = AssertionError::class)
    fun setNumber_afterPost_shouldAssert() {
        val v = View(context)
        val subject = UIEvent(1234)
        subject.post(v)

        subject.setNumber(987)
    }

    @Test(expected = AssertionError::class)
    fun setNamespace_afterPost_shouldAssert() {
        val v = View(context)
        val subject = UIEvent(1234)
        subject.post(v)

        subject.setNamespace("some_namespace")
    }

    @Test
    fun toParcel_fromParcel_shouldReturnValidCopy() {
        val parcel = Parcel.obtain()
        val subject = UIEvent(9876, "unique_string")
                .setNamespace("namespace value")
                .setNumber(2345)
                .setText("text value")

        subject.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        val copy = UIEvent(parcel)

        assertEquals(subject, copy)
    }
}
