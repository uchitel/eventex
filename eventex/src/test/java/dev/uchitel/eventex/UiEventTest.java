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

import android.os.Parcel;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class UiEventTest {

    @Test
    public void postView_shouldSetFlagSent(){
        View v = new View(RuntimeEnvironment.systemContext);
        UiEvent subject = new UiEvent(1234);
        assertFalse(subject.isSent());

        subject.post(v);

        assertTrue(subject.isSent());
    }

    @Test
    public void toParcel_fromParcel_shouldReturnValidCopy() {
        Parcel parcel = Parcel.obtain();
        UiEvent subject = new UiEvent(9876, "unique_string")
                .setNamespace("namespace value")
                .setNumber(2345)
                .setText("text value");

        subject.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        UiEvent copy = new UiEvent(parcel);

        assertEquals(subject, copy);
    }
}
