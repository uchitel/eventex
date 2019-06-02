##  Eventex, Android Express Events

<table border="0">
 <tr>
    <td>Android library to exchange messages between Fragments, ViewGroups, Activity.
No need to create interfaces and pass listeners to multiple classes.
There is also no need to subscribe/unsibscribe for events!
</td>
    <td width="386"><img width="384" height="256"  src="icons/eventex-android-library-256.png"></td>
 </tr>
</table>

### Simple
To post message
``` java
new UIEvent("button.ok.click").post(view); // yes, this is it
```
To receive message. In any class that extends Fragment, ViewGroup, or Activity
``` java
public class CustomFragment extends Fragment implements UIEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        switch (uiEvent.what) {
            case "button.ok.click":
                Log.d(uiEvent.toString());
                return true; // to stop message propagation
        }
        return false;   // to let other objects to process message
    }
}
```
Much less boilerplate code compare to classic solution
<a href="https://developer.android.com/training/basics/fragments/communicating.html">Communicate with other fragments</a>!
Class CustomFragment extends Android class Fragment. It will also
work well if the class extends Activity, ViewGroup, or any layout
derived from ViewGroup (LinearLayout, FrameLayout, etc..)

### Features
- asynchronously deliver messages between components of an Activity.
- no need to subscribe/unsubscribe to receive messages.
- completely decouples components.
- no reflection and no ProGuard rules.
- tiny code size.

### Requirements
- Android 4.1.0(API 16) or above.
- Java 8

### Prerequisites
Make sure Java 8 (1.8) support is enabled in the gradle file
```
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```
Add EventEx to the project gradle file (Android**x** based projects)
```
implementation 'dev.uchitel:eventex:0.2.0'
```

Or for Android Support Library projects
```
implementation 'dev.uchitel:eventex-support:0.2.0'
```

### More Details

Message can carry additional integer and string value:
``` java
new UIEvent(12345)
    .setText("some text to pass with message")
    .setNumber(9876) // some integer to pass with message
    .post(viewGroup);
```
Next code will properly receive this message:
``` java
public class FragmentReceiver extends Fragment implements UIEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) return false;

        switch (uiEvent.code) {
            case 12345:
                Log.d("FragmentReceiver", "text="+uiEvent.getText());
                Log.d("FragmentReceiver", "number="+uiEvent.getNumber());
                return true; // to stop message propagation
        }
        return false;   // to let other components to process the message
    }
}
```

Message can use integer ID, string ID, or both for more complex control scenarios:
``` java
new UIEvent(12345, "button.ok.click"))
    .post(view);
```
The 'onMessage' for the above code:
``` java
public class FragmentReceiver extends Fragment implements UIEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (!uiEvent.isAppNamespace()) return false;

        switch (uiEvent.code) {
            case 12345:
                if(uiEvent.what.equals("button.ok.click")){
                    // ...
                    return true; // to stop message propagation
                }
        }
        return false;   // to let other components to process the message
    }
}
```

When writing android library make sure to use 'namespace' to prevent collisions. Sending message inside library can look like:
``` java
new UIEvent("button.ok.click")
    .setNamespace("lib_name.company_name.com")
    .post(view);
```
Namespace "lib_name.company_name.com" is going to prevent ID collisions
when the library is distributed to third party developers.

And to receive this message inside library module
``` java
public class FragmentReceiver extends Fragment implements UIEventListener {
//  .....
    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        // return if this is not library message
        if (!uiEvent.getNamespace().equals("libname.company.com")) return false;

        switch (uiEvent.what){
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

#### Do you think it might be useful? Help devs to find it.
[<img src="icons/twitter.png" width=64>](https://twitter.com/intent/tweet?text=%23androiddev%20Delete%20all%20those%20listeners%20from%20your%20application...&url=https://github.com/uchitel/eventex)
[<img src="icons/reddit.png" width=64>](http://www.reddit.com/submit?url=https://github.com/uchitel/eventex&title=Android%20Must%20Use%20Library)
[<img src="icons/linkedin.png" width=64>](https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/uchitel/eventex&title=Android%20Must%20Use%20Library&summary=Delete%20all%20those%20listeners%20from%20your%20Android%20application...&source=https://github.com/uchitel/eventex)
[<img src="icons/facebook.png" width=64>](https://www.facebook.com/sharer/sharer.php?u=https://github.com/uchitel/eventex)
[<img src="icons/vkontakte.png" width=64>](https://vk.com/share.php?url=https://github.com/uchitel/eventex&title=Android%20Must%20Use%20Library&description=Delete%20all%20those%20listeners%20from%20your%20application...&image=https://github.com/uchitel/eventex/icons/eventex-android-library-256.png&noparse=true)
[<img src="icons/xing.png" width=64>](https://www.xing-share.com/app/user?op=share;sc_p=xing-share;url=https://github.com/uchitel/eventex)

#### Alternative libraries
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
