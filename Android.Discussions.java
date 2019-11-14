GitHub Repository
___________________________________________
	https://github.com/techhue/Accenture.Android

	Download Following Android Code and Unzip It
		Android.Code1.zip
		Android.Code2.zip
		Android.Code.Fragements.zip

	Explore Following Code Examples
		Android.Code1 > Project03
			Project.03.01.ManifestAndResources
			Project.03.02.Snippets
			Project.03.03.ConfigChanges
			Project.03.04.Activities

		Android.Code2 > Project04
			Project04.01.Layouts
			Project04.03.Views
				Discussion and Analysis

			Project04.04.Adapters
				Discussion and Analysis

		Android.Code.Fragements
			android_fundamentals_demo-master
			fb-fragments-demo-master
			android-fragment-basics-master
		
		Android.Code3 > Project05
			Project.05.01.Intents
			Project.05.03.BoadcastIntents


Reading Assignments
___________________________________________
Day 1:
	https://developer.android.com/studio/run/emulator

Day 2:
	https://developer.android.com/reference/android/app/Activity
	https://developer.android.com/guide/topics/resources/runtime-changes

Day 3:
	Reference Purpose: To Explore How Many Scenarios For Which onConfigurationChange Get Called
	https://medium.com/hootsuite-engineering/handling-orientation-changes-on-android-41a6b62cb43f
	
	https://guides.codepath.com/android/Constructing-View-Layouts#using-alignment-to-control-width-or-height

	https://developer.android.com/reference/android/widget/ListView

Day 4:
	https://developer.android.com/reference/android/widget/ListView
	
	https://developer.android.com/guide/components/activities/tasks-and-back-stack

	https://developer.android.com/guide/components/activities/activity-lifecycle
	https://developer.android.com/reference/android/app/Activity
	https://developer.android.com/guide/components/fragments


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


___________________________________________
Design Thinking Through Design Laws
___________________________________________
1. Design For Graceful Failure
2. Design Minimal Viable Product
	Most Used Minimum and Complete Features Set
	Make It Work For Maximum Number Of Scenarios
3. Design Towards Ownership
	Corollary: Design Towards Memory Management
	Never Ever Mess with Ownership Design
4. Design Towards Localization
	Never Ever Violate Locationzation Principles

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

___________________________________________
onConfigurationChange Recommended Practice
___________________________________________

Caution: Handling the configuration change yourself can make it much more difficult to use alternative resources, because the system does not automatically apply them for you. This technique should be considered a last resort when you must avoid restarts due to a configuration change and is not recommended for most applications.

	
 	android:configChanges=["mcc", "mnc", "locale",
                                 "touchscreen", "keyboard", "keyboardHidden",
                                 "navigation", "screenLayout", "fontScale",
                                 "uiMode", "orientation", "density",
                                 "screenSize", "smallestScreenSize"]


If the configuration of the device (as defined by the Configuration class) changes, then anything displaying a user interface will need to update to match that configuration. Because Activity is the primary mechanism for interacting with the user, it includes special support for handling configuration changes.

Unless you specify otherwise, a configuration change (such as a change in screen orientation, language, input devices, etc) will cause your current activity to be destroyed, going through the normal activity lifecycle process of onPause(), onStop(), and onDestroy() as appropriate. If the activity had been in the foreground or visible to the user, once onDestroy() is called in that instance then a new instance of the activity will be created, with whatever savedInstanceState the previous instance had generated from onSaveInstanceState(Bundle).

This is done because any application resource, including layout files, can change based on any configuration value. Thus the only safe way to handle a configuration change is to re-retrieve all resources, including layouts, drawables, and strings. Because activities must already know how to save their state and re-create themselves from that state, this is a convenient way to have an activity restart itself with a new configuration.

In some special cases, you may want to bypass restarting of your activity based on one or more types of configuration changes. This is done with the android:configChanges attribute in its manifest. For any types of configuration changes you say that you handle there, you will receive a call to your current activity's onConfigurationChanged(Configuration) method instead of being restarted. If a configuration change involves any that you do not handle, however, the activity will still be restarted and onConfigurationChanged(Configuration) will not be called.


