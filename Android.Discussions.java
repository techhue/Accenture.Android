
Submit WarGame Code Till Thursday 7:00 AM
______________________________________________
WarGame v1 : Till Yesterday
	Send Email:
		Email Title : War Game GitHub Repository
		Email Body	: GitHub Repository Link
					  Your Name

WarGame v2 : Till Today
	Send Email:
		Email Title : War Game GitHub Repository
		Email Body	: GitHub Repository Link
					  Your Name

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

		Android.Code4 > Project06
			Project.06.01.Internet

		Android.Code5 > Project08
			Project.08.01.DatabaseSkeleton
			Project.08.02.ContentProviders

		Android.Code.Services
			Project09.01.MyService
			android-services-demo-master


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

Day 7:
	https://developer.android.com/guide/components/services


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
5. Name is An Idea! Never Mess With It!
6. You Should Not Pay For It! If You Are Not Using It!

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

___________________________________________
Android Services
___________________________________________

A Service is an application component that can perform long-running operations in the background, and it doesn't provide a user interface. 


Another application component can start a service, and it continues to run in the background even if the user switches to another application. 


Additionally, a component can bind to a service to interact with it and even perform interprocess communication (IPC). 

For example, a service can handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background.

These are the three different types of services:


Foreground Bucket

	A foreground service performs some operation that is noticeable to the user. 

	For Example:
		Download In Service( In Background ) and Updating UI

	For Example: an audio app would use a foreground service to play an audio track. 
		Foreground services must display a Notification. 
	
	NOTE : Foreground services continue running even when the user isn't interacting with the app.


Background Bucket

	A background service performs an operation that isn't directly noticed by the user. 

	For Example:
		Angry Bird Background Music is Played In Background Service
	
	For Example: if an app used a service to compact its storage, that would usually be a background service.

	Note: If your app targets API level 26 or higher, the system imposes restrictions on running background services when the app itself isn't in the foreground. 

		In most cases like this, your app should use a scheduled job instead.

Bound Bucket

	A service is bound when an application component binds to it by calling bindService(). 

	A bound service offers a client-server interface that allows components to interact with the service, send requests, receive results, and even do so across processes with interprocess communication (IPC). 

	A bound service runs only as long as another application component is bound to it. Multiple components can bind to the service at once, but when all of them unbind, the service is destroyed.

VERY IMPORTANT
	Although this documentation generally discusses started and bound services separately

	your service can work both ways—it can be started (to run indefinitely) and also allow binding. 

	It's simply a matter of whether you implement a couple of callback methods: onStartCommand() to allow components to start it and onBind() to allow binding.


Regardless 
	of whether your service is started, bound, or both, 

	Any application component can use the service (even from a separate application) in the same way that any component can use an activity—by starting it with an Intent. 

	However, you can declare the service as private in the manifest file and block access from other applications. This is discussed more in the section about Declaring the service in the manifest.

CAUTION:

	A service runs in the main thread of its hosting process; the service does not create its own thread and does not run in a separate process unless you specify otherwise. 

	If your service is going to perform any CPU-intensive work or blocking operations, such as MP3 playback or networking, 

	you should create a new thread within the service to complete that work. By using a separate thread, you can reduce the risk of Application Not Responding (ANR) errors, and the application's main thread can remain dedicated to user interaction with your activities.


Choosing between a service and a thread
___________________________________________

	A service is simply a component that can run in the background(provided develper design so), 

	Even when the user is not interacting with your application, so you should create a service only if that is what you need.

	If you must perform work outside of your main thread, but only while the user is interacting with your application, you should instead create a new thread. 

	For Example:
		if you want to play some music, but only while your activity is running, you might create a thread in onCreate(), start running it in onStart(), and stop it in onStop(). 

		Also consider using AsyncTask or HandlerThread instead of the traditional Thread class. See the Processes and Threading document for more information about threads.

REMEMBER:
	 that if you do use a service, it still runs in your application's main thread by default, 

	 so you should still create a new thread within the service if it performs intensive or blocking operations.


___________________________________________
Service Class The basics
___________________________________________

To create a service, you must create a subclass of Service or use one of its existing subclasses. 


	In your implementation, you must override some callback methods that handle key aspects of the service lifecycle and provide a mechanism that allows the components to bind to the service, if appropriate. 

	These are the most important callback methods that you should override:

	onCreate()
		The system invokes this method to perform one-time setup procedures when the service is initially created (before it calls either onStartCommand() or onBind()). 

		If the service is already running, this method is not called.

	onStartCommand()

		The system invokes this method by calling startService() when another component (such as an activity) requests that the service be started. 

		When this method executes, the service is started and can run in the background indefinitely. 

		If you implement this, it is your responsibility to stop the service when its work is complete by calling stopSelf() or stopService(). 

		If you only want to provide binding, you don't need to implement this method.

	onBind()

		The system invokes this method by calling bindService() when another component wants to bind with the service (such as to perform RPC). 

		In your implementation of this method, you must provide an interface that clients use to communicate with the service by returning an IBinder. 

		You must always implement this method; however, if you don't want to allow binding, you should return null.


	onDestroy()

		The system invokes this method when the service is no longer used and is being destroyed. 

		Your service should implement this to clean up any resources such as threads, registered listeners, or receivers. This is the last call that the service receives.

		If a component starts the service by calling startService() (which results in a call to onStartCommand()), the service continues to run until it stops itself with stopSelf() or another component stops it by calling stopService().


	If a component calls bindService() to create the service and onStartCommand() is not called, the service runs only as long as the component is bound to it. After the service is unbound from all of its clients, the system destroys it.


IMPORTANT
	The Android system stops a service only when memory is low and it must recover system resources for the activity that has user focus. 

	Foreground Bucket
		If the service is bound to an activity that has user focus, it's less likely to be killed; if the service is declared to run in the foreground, it's rarely killed. 

	Background Bucket
		If the service is started and is long-running, the system lowers its position in the list of background tasks over time	, and the service becomes highly susceptible to killing—

	DESIGN DECISION: REMEMBER Any Components Can Come and Go Anytime!
		if your service is started, you must design it to gracefully handle restarts by the system. 

		If the system kills your service, it restarts it as soon as resources become available, but this also depends on the value that you return from onStartCommand(). 

		For more information about when the system might destroy a service, see the Processes and Threading document.

	There are other attributes that you can include in the <service> element to define properties such as the permissions that are required to start the service and the process in which the service should run. 

	The android:name attribute is the only required attribute—it specifies the class name of the service. 

	After you publish your application, leave this name unchanged to avoid the risk of breaking code due to dependence on explicit intents to start or bind the service (read the blog post, Things That Cannot Change).


CAUTION:
	Caution: To ensure that your app is secure, 

	Always use an explicit intent when starting a Service and don't declare intent filters for your services. 

	Using an implicit intent to start a service is a security hazard because you cannot be certain of the service that responds to the intent, and the user cannot see which service starts.

	Beginning with Android 5.0 (API level 21), the system throws an exception if you call bindService() with an implicit intent.

	You can ensure that your service is available to only your app by including the android:exported attribute and setting it to false. 
	This effectively stops other apps from starting your service, even when using an explicit intent.

IMPORTANT NOTE
	Users can see what services are running on their device. If they see a service that they don't recognize or trust, they can stop the service. 

	In order to avoid having your service stopped accidentally by users, you need to add the android:description attribute to the <service> element in your app manifest. 

	In the description, provide a short sentence explaining what the service does and what benefits it provides.

IMPORTANT NOTE: Stopping Service

	When a service is started, it has a lifecycle that's independent of the component that started it. 

	The service can run in the background indefinitely, even if the component that started it is destroyed. As such, the service should stop itself when its job is complete by calling stopSelf(), or another component can stop it by calling stopService().


	An application component such as an activity can start the service by calling startService() and passing an Intent that specifies the service and includes any data for the service to use. 

	The service receives this Intent in the onStartCommand() method.


	For Example: Syncing Data To Server/DataBase
		For instance, suppose an activity needs to save some data to an online database. The activity can start a companion service and deliver it the data to save by passing an intent to startService(). T

		the service receives the intent in onStartCommand(), connects to the Internet, and performs the database transaction. When the transaction is complete, the service stops itself and is destroyed.

CAUTION:
	A service runs in the same process as the application in which it is declared and in the main thread of that application by default. 

	If your service performs intensive or blocking operations while the user interacts with an activity from the same application, the service slows down activity performance. 

	To avoid impacting application performance, start a new thread inside the service.


Two classes you can extend to create a started service:

Service
	This is the base class for all services. When you extend this class, it's important to create a new thread in which the service can complete all of its work; the service uses your application's main thread by default, which can slow the performance of any activity that your application is running.

IntentService
	This is a subclass of Service that uses a worker thread to handle all of the start requests, one at a time. This is the best option if you don't require that your service handle multiple requests simultaneously. 

	Implement onHandleIntent(), which receives the intent for each start request so that you can complete the background work.

BEST PRACTICES
	Extending Service or IntentService class

	Because most of the started services don't need to handle multiple requests simultaneously (which can actually be a dangerous multi-threading scenario), 

	it's best that you implement your service using the IntentService class.

___________________________________________
The IntentService class does the following:
___________________________________________

	It creates a default worker thread that executes all of the intents that are delivered to onStartCommand(), separate from your application's main thread.

	Creates a work queue that passes one intent at a time to your onHandleIntent() implementation, so you never have to worry about multi-threading.

	Stops the service after all of the start requests are handled, so you never have to call stopSelf().

	Provides a default implementation of onBind() that returns null.

	Provides a default implementation of onStartCommand() that sends the intent to the work queue and then to your onHandleIntent() implementation.

	If you decide to also override other callback methods, such as onCreate(), onStartCommand(), or onDestroy(), be sure to call the super implementation so that the IntentService can properly handle the life of the worker thread.

	Besides onHandleIntent(), the only method from which you don't need to call the super class is onBind(). You need to implement this only if your service allows binding


Extending the Service class
	Using IntentService makes your implementation of a started service very simple. If, however, you require your service to perform multi-threading (instead of processing start requests through a work queue), you can extend the Service class to handle each intent.



The return value from onStartCommand() must be one of the following constants:

START_NOT_STICKY

	If the system kills the service after onStartCommand() returns, 

		do not recreate the service unless there are pending intents to deliver. 

	This is the safest option to avoid running your service when not necessary and when your application can simply restart any unfinished jobs.

START_STICKY

	If the system kills the service after onStartCommand() returns,
	 	recreate the service and call onStartCommand(), but do not redeliver the last intent. 

	 Instead, the system calls onStartCommand() with a null intent unless there are pending intents to start the service.

	  In that case, those intents are delivered. This is suitable for media players (or similar services) that are not executing commands but are running indefinitely and waiting for a job.

START_REDELIVER_INTENT
	
	If the system kills the service after onStartCommand() returns,
	 recreate the service and call onStartCommand() with the last intent that was delivered to the service. 

	 Any pending intents are delivered in turn. 

	 This is suitable for services that are actively performing a job that should be immediately resumed, such as downloading a file.


IMPORTANT NOTE:

	Note: If your app targets API level 26 or higher, the system imposes restrictions on using or creating background services unless the app itself is in the foreground(Bucket). 

	If an app needs to create a foreground(Bucket) service, the app should call startForegroundService(). 

	That method creates a background(Bucket) service, but the method signals to the system that the service will promote itself to the foreground(Bucket). 

	Once the service has been created, the service must call its startForeground() method within five seconds.


The startService() method returns immediately, and the Android system calls the service's onStartCommand() method. If the service isn't already running, the system first calls onCreate(), and then it calls onStartCommand().



If the service doesn't also provide binding, 
	the intent that is delivered with startService() is the only mode of communication between the application component and the service. 

	However, if you want the service to send a result back, the client that starts the service can create a PendingIntent for a broadcast (with getBroadcast()) and deliver it to the service in the Intent that starts the service. 

	The service can then use the broadcast to deliver a result.


Multiple requests to start the service result in multiple corresponding calls to the service's onStartCommand(). However, only one request to stop the service (with stopSelf() or stopService()) is required to stop it.


___________________________________________
Stopping Service
___________________________________________

If your service handles multiple requests to onStartCommand() concurrently, you shouldn't stop the service when you're done processing a start request, as you might have received a new start request (stopping at the end of the first request would terminate the second one). To avoid this problem, you can use stopSelf(int) to ensure that your request to stop the service is always based on the most recent start request. That is, when you call stopSelf(int), you pass the ID of the start request (the startId delivered to onStartCommand()) to which your stop request corresponds. Then, if the service receives a new start request before you are able to call stopSelf(int), the ID doesn't match and the service doesn't stop.

CAUTION:
	Caution: To avoid wasting system resources and consuming battery power, ensure that your application stops its services when it's done working. If necessary, other components can stop the service by calling stopService(). Even if you enable binding for the service, you must always stop the service yourself if it ever receives a call to onStartCommand().

___________________________________________
Creating a bound service
___________________________________________

	A bound service is one that allows application components to bind to it by calling bindService() to create a long-standing connection. It generally doesn't allow components to start it by calling startService().

	Create a bound service when you want to interact with the service from activities and other components in your application or to expose some of your application's functionality to other applications through interprocess communication (IPC).

	To create a bound service, implement the onBind() callback method to return an IBinder that defines the interface for communication with the service. Other application components can then call bindService() to retrieve the interface and begin calling methods on the service. 

	The service lives only to serve the application component that is bound to it, so when there are no components bound to the service, the system destroys it. 

	You do not need to stop a bound service in the same way that you must when the service is started through onStartCommand().


	To create a bound service, you must define the interface that specifies how a client can communicate with the service. This interface between the service and a client must be an implementation of IBinder and is what your service must return from the onBind() callback method. After the client receives the IBinder, it can begin interacting with the service through that interface.

	Multiple clients can bind to the service simultaneously. When a client is done interacting with the service, it calls unbindService() to unbind. When there are no clients bound to the service, the system destroys the service.

	There are multiple ways to implement a bound service, and the implementation is more complicated than a started service. For these reasons, the bound service discussion appears in a separate document about Bound Services.


___________________________________________
Sending notifications to the user
___________________________________________


	When a service is running, it can notify the user of events using Toast Notifications or Status Bar Notifications.

	A toast notification is a message that appears on the surface of the current window for only a moment before disappearing. A status bar notification provides an icon in the status bar with a message, which the user can select in order to take an action (such as start an activity).

	Usually, a status bar notification is the best technique to use when background work such as a file download has completed, and the user can now act on it. When the user selects the notification from the expanded view, the notification can start an activity (such as to display the downloaded file).

See the Toast Notifications or Status Bar Notifications developer guides for more information.


______________________________________________
Accessing a provider
______________________________________________

	When you want to access data in a content provider, you use the ContentResolver object in your application's Context to communicate with the provider as a client. 

	The ContentResolver object communicates with the provider object, an instance of a class that implements ContentProvider.

	 The provider object receives data requests from clients, performs the requested action, and returns the results. 

	 This object has methods that call identically-named methods in the provider object, an instance of one of the concrete subclasses of ContentProvider. 

	 The ContentResolver methods provide the basic "CRUD" (create, retrieve, update, and delete) functions of persistent storage


______________________________________________
CursorLoader: Does CRUD IN Background Threads
______________________________________________

	A common pattern for accessing a ContentProvider from your UI uses a CursorLoader to run an asynchronous query in the background. 

	The Activity or Fragment in your UI call a CursorLoader to the query, which in turn gets the ContentProvider using the ContentResolver. 

	Note: 
	To access a provider, your application usually has to request specific permissions in its manifest file. This development pattern is described in more detail in the section Content Provider Permissions.

	Content URIs
	A content URI is a URI that identifies data in a provider. 

	Content URIs include the symbolic name of the entire provider (its authority) and a name that points to a table (a path). 

	When you call a client method to access a table in a provider, the content URI for the table is one of the arguments.

______________________________________________
Data access via intents
______________________________________________

	Intents can provide indirect access to a content provider. 

		You allow the user to access data in a provider even if your application doesn't have access permissions, 

		either by getting a result intent back from an application that has permissions, 

		or by activating an application that has permissions and letting the user do work in it.


	Getting access with temporary permissions
		You can access data in a content provider, even if you don't have the proper access permissions, 

		by sending an intent to an application that does have the permissions and receiving back a result intent containing "URI" permissions. 

		These are permissions for a specific content URI that last until the activity that receives them is finished. 

		The application that has permanent permissions grants temporary permissions by setting a flag in the result intent:

			Read permission: FLAG_GRANT_READ_URI_PERMISSION
			Write permission: FLAG_GRANT_WRITE_URI_PERMISSION

Note: These flags don't give general read or write access to the provider whose authority is contained in the content URI. The access is only for the URI itself.


A provider defines URI permissions for content URIs in its manifest, 

	using the android:grantUriPermission attribute
		 of the <provider> element, as well as the 
		 <grant-uri-permission> child element of the <provider> element

		 The URI permissions mechanism is explained in more detail in the Permissions overview guide.


For Example: 
	you can retrieve data for a contact in the Contacts Provider, 
	even if you don't have the READ_CONTACTS permission. 

	You might want to do this in an application that sends e-greetings to a contact on their birthday. 

	Instead of requesting READ_CONTACTS, which gives you access to all of the user's contacts and all of their information, you prefer to let the user control which contacts are used by your application. To do this, you use the following process:
______________________________________________
Contract Classes
______________________________________________

	A contract class defines constants that help applications work with the content URIs, column names, intent actions, and other features of a content provider. 

	Contract classes are not included automatically with a provider; the provider's developer has to define them and then make them available to other developers. Many of the providers included with the Android platform have corresponding contract classes in the package android.provider.


