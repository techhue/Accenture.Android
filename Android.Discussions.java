
Reading Assignments
___________________________________________
Day 1:
https://developer.android.com/studio/run/emulator

Day 2:
https://developer.android.com/reference/android/app/Activity
https://developer.android.com/guide/topics/resources/runtime-changes


Android Application Architecture
___________________________________________
1. Multiprocess Architecture
2. Multi-Entry and Multi-Exit Architecture
3. Android Application is:
		Set of Cooperative Components
		Components are 4 Types 
			Activity, Service, 
			Broadcast Reciever, Content Provider
		Components Cooperate Through Message Passing
			e.g Using Intent
		Assume: Any Component Can Come and Go Any Time!

Design Thinking
___________________________________________
1. Design For Graceful Failure
2. Design Minimal Viable Product
	Most Used Minimum and Complete Features Set
	Make It Work For Maximum Number Of Scenarios



___________________________________________

There are three key loops you may be interested in monitoring within your activity:

The entire lifetime of an activity happens between the first call to onCreate(Bundle) through to a single final call to onDestroy(). An activity will do all setup of "global" state in onCreate(), and release all remaining resources in onDestroy(). For example, if it has a thread running in the background to download data from the network, it may create that thread in onCreate() and then stop the thread in onDestroy().

The visible lifetime of an activity happens between a call to onStart() until a corresponding call to onStop(). During this time the user can see the activity on-screen, though it may not be in the foreground and interacting with the user. Between these two methods you can maintain resources that are needed to show the activity to the user. For example, you can register a BroadcastReceiver in onStart() to monitor for changes that impact your UI, and unregister it in onStop() when the user no longer sees what you are displaying. The onStart() and onStop() methods can be called multiple times, as the activity becomes visible and hidden to the user.

The foreground lifetime of an activity happens between a call to onResume() until a corresponding call to onPause(). During this time the activity is in visible, active and interacting with the user. An activity can frequently go between the resumed and paused states -- for example when the device goes to sleep, when an activity result is delivered, when a new intent is delivered -- so the code in these methods should be fairly lightweight.




The entire lifecycle of an activity is defined by the following Activity methods. All of these are hooks that you can override to do appropriate work when the activity changes state. All activities will implement onCreate(Bundle) to do their initial setup; many will also implement onPause() to commit changes to data and prepare to pause interacting with the user, and onStop() to handle no longer being visible on screen. 

	You should always call up to your superclass when implementing these methods.


Note the "Killable" column in the above table -- for those methods that are marked as being killable, after that method returns the process hosting the activity may be killed by the system at any time without another line of its code being executed. Because of this, you should use the onPause() method to write any persistent data (such as user edits) to storage. 

	In addition, the method onSaveInstanceState(android.os.Bundle) is called before placing the activity in such a background state, allowing you to save away any dynamic instance state in your activity into the given Bundle, to be later received in onCreate(Bundle) if the activity needs to be re-created. See the Process Lifecycle section for more information on how the lifecycle of a process is tied to the activities it is hosting. Note that it is important to save persistent data in onPause() instead of onSaveInstanceState(Bundle) because the latter is not part of the lifecycle callbacks, so will not be called in every situation as described in its documentation.

Be aware that these semantics will change slightly between applications targeting








