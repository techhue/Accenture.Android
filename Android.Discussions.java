
GitHub Repository
______________________________________________
	https://github.com/HariPrasadSala/WapApp
	https://github.com/Bibhukalyan/WarAppV1
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
			Project.05.02.Linkify
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

Day 6:
	https://developer.android.com/guide/components/fragments
	https://developer.android.com/guide/components/intents-filters
	https://developer.android.com/guide/components/broadcasts
	https://developer.android.com/guide/components/broadcast-exceptions.html

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


___________________________________________

/**
 * Base class for activities that use the
 * <a href="{@docRoot}tools/extras/support-library.html">support library</a> action bar features.
 *
 * <p>You can add an {@link androidx.appcompat.app.ActionBar} to your activity when running on API level 7 or higher
 * by extending this class for your activity and setting the activity theme to
 * {@link androidx.appcompat.R.style#Theme_AppCompat Theme.AppCompat} or a similar theme.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 *
 * <p>For information about how to use the action bar, including how to add action items, navigation
 * modes and more, read the <a href="{@docRoot}guide/topics/ui/actionbar.html">Action
 * Bar</a> API guide.</p>
 * </div>
 */
public class AppCompatActivity extends FragmentActivity implements AppCompatCallback,
        TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider {

___________________________________________
Broadcast Reciever
___________________________________________

Android apps(compoents) can send or receive broadcast messages from the Android system and other Android apps, similar to the 
	publish-subscribe design pattern. 
	---------------------------------
These broadcasts are sent when an event of interest occurs. 

For example, the Android system sends broadcasts when various system events occur, 
	such as when the system boots up or the device starts charging. 
	Apps can also send custom broadcasts, 
		for example, to notify other apps of something that they might be interested in 
			(for example, some new data has been downloaded).

___________________________________________
Intent and Intent Filters
___________________________________________

An Intent is a messaging object you can use to request an action from another app component. Although intents facilitate communication between components in several ways, there are three fundamental use cases:

___________________________________________
onSaveInstanceState
___________________________________________

/**
 * Called to retrieve per-instance state from an activity before being killed
 * so that the state can be restored in {@link #onCreate} or
 * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
 * will be passed to both).
 *
 
 * <p>This method is called before an activity may be killed so that when it
 * comes back some time in the future it can restore its state.  

 For example,
 * if activity B is launched in front of activity A, and at some point activity
 * A is killed to reclaim resources, activity A will have a chance to save the
 * current state of its user interface via this method so that when the user
 * returns to activity A, the state of the user interface can be restored
 * via {@link #onCreate} or {@link #onRestoreInstanceState}.


 *
 * <p>Do not confuse this method with activity lifecycle callbacks such as
 * {@link #onPause}, which is always called when an activity is being placed
 * in the background or on its way to destruction, or {@link #onStop} which
 * is called before destruction.  


 One example of when {@link #onPause} and
 * {@link #onStop} is called and not this method is when a user navigates back
 * from activity B to activity A: there is no need to call {@link #onSaveInstanceState} * on B because that particular instance will never be restored, so the system avoids calling it.  

 An example when {@link #onPause} is called and
 * not {@link #onSaveInstanceState} is when activity B is launched in front of activity A: the system may avoid calling {@link #onSaveInstanceState} on activity A if it isn't killed during the lifetime of B since the state of the user interface of
 
 * A will stay intact.
 *
 * <p>The default implementation takes care of most of the UI per-instance
 * state for you by calling {@link android.view.View#onSaveInstanceState()} on each
 * view in the hierarchy that has an id, and by saving the id of the currently
 * focused view (all of which is restored by the default implementation of
 * {@link #onRestoreInstanceState}).  If you override this method to save additional
 * information not captured by each individual view, you will likely want to
 * call through to the default implementation, otherwise be prepared to save
 * all of the state of each view yourself.
 *
 * <p>If called, this method will occur after {@link #onStop} for applications
 * targeting platforms starting with {@link android.os.Build.VERSION_CODES#P}.
 * For applications targeting earlier platform versions this method will occur
 * before {@link #onStop} and there are no guarantees about whether it will
 * occur before or after {@link #onPause}.
 *
 * @param outState Bundle in which to place your saved state.
 *
 * @see #onCreate
 * @see #onRestoreInstanceState
 * @see #onPause
 */
protected void onSaveInstanceState(Bundle outState) {

/**
 * This is the same as {@link #onSaveInstanceState} but is called for activities
 * created with the attribute {@link android.R.attr#persistableMode} set to
 * <code>persistAcrossReboots</code>. The {@link android.os.PersistableBundle} passed
 * in will be saved and presented in {@link #onCreate(Bundle, PersistableBundle)}
 * the first time that this activity is restarted following the next device reboot.
 *
 * @param outState Bundle in which to place your saved state.
 * @param outPersistentState State which will be saved across reboots.
 *
 * @see #onSaveInstanceState(Bundle)
 * @see #onCreate
 * @see #onRestoreInstanceState(Bundle, PersistableBundle)
 * @see #onPause
 */
public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    onSaveInstanceState(outState);
}


