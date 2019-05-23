##  EventEx, Android Express Events

Android library to exchange messages/events between Fragments, ViewGroups, Activity. No need to pass listener to
multiple classes to have it available insided RecyclerView.Adapter or some other decoupled file. 
Less boilerplate code compare to 
[Communicate with other fragments](https://developer.android.com/training/basics/fragments/communicating.html).

This library delivers messages between components of single Activity.
Use Android broadcast to exchange messages between services, activities...

### Features
- simplifies communication between Android UI components.
- no need to subscribe/unsubscribe to receive messages.
- completely decouples components, posts messages asynchronously.
- no reflection and no ProGuard rules
- tiny code size.

### Requirements
- Android 4.0.1(API 14) or above.
- Java 8
- Androidx or Android support library


### Usage
Enable Java 8 (1.8) support in the gradle file
```
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```

### Simple
To Send message
``` java
new UiEvent("button.ok.click")  // unique message identifier
        .setText("some text message")     // optinal text string
        .post(view);   // can be ViewGroup, Fragment or Activity instance.
```

To receive the above message. In any class that extends Fragment, ViewGroup, or Activity
``` java
public class FragmentReceiver extends Fragment implements UiEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UiEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) return false;

        switch (uiEvent.what()) {
            case "button.ok.click":
                Log.d(uiEvent.getText());
                return true; // to stop message propagation
        }
        return false;   // to let other objects to process message
    }
}
```
Class FragmentReceiver extends Android class Fragment. It will also
work well if the class extends Activity, ViewGroup, or any layout
derived from ViewGroup: LinearLayout, FrameLayout, etc..

### More Details
Message can use integer value instead of string as a unique message ID:
``` java
new UiEvent(12345).post(viewGroup);
```
Next code will properly receive this message:
``` java
public class FragmentReceiver extends Fragment implements UiEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UiEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) return false;

        switch (uiEvent.code()) {
            case 12345:
                Log.d(uiEvent.toString());
                return true; // to stop message propagation
        }
        return false;   // to let other components to process message
    }
}
```

Message can use both integer and string IDs for more complex control scenarios:
``` java
new UiEvent(12345, "button.ok.click"))
    .post(view);
```
When writing android library make sure to use 'namespace' to prevent collisions. Sending message inside library can look like:
``` java
new UiEvent("button.ok.click")
    .setNamespace("lib_name.company_name.com")
    .post(view);
```
Namespace "lib_name.company_name.com" is going to prevent collisions
when your library is distributed to third party developers.

And to receive this message inside library module
``` java
public class FragmentReceiver extends Fragment implements UiEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UiEvent uiEvent) {
        // return if this is not library message
        if (!uiEvent.getNamespace().equals("libname.company.com")) return false;

        switch (uiEvent.what()){
            case "button.ok.click":
                Log.d(uiEvent.getText());
                return true; // to stop message propagation
        }
        return false;   // to let other objects to process message
    }
}
```

### R8 / ProGuard
No special requirements for R8 or ProGuard

### Alternative libraries
 - [Event Bus](https://github.com/greenrobot/EventBus)
 
### License

    Copyright 2019 Alexander Uchitel

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
