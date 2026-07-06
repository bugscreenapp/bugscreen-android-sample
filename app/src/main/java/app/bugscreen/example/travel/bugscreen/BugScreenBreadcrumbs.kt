package app.bugscreen.example.travel.bugscreen

import app.bugscreen.android.BugScreenSDK
import app.bugscreen.android.LogLevel

/**
 * Thin wrapper around [BugScreenSDK.log]. Every log line recorded here is automatically
 * attached to any bug report the user submits, giving you a breadcrumb trail of what they
 * did just before hitting a problem.
 *
 * Funnelling the calls through one object keeps the rest of the app from importing the SDK
 * directly — so screen code reads like a normal app and the debug/no-op swap stays invisible.
 * Add your own events here the same way you'd add analytics.
 */
object BugScreenBreadcrumbs {

    fun screenViewed(name: String) =
        BugScreenSDK.log(LogLevel.INFO, "screen_viewed: $name")

    fun destinationOpened(id: String) =
        BugScreenSDK.log(LogLevel.INFO, "destination_opened: $id")

    fun saveToggled(id: String, saved: Boolean) =
        BugScreenSDK.log(LogLevel.DEBUG, "save_toggled id=$id saved=$saved")

    fun tripOpened(id: String) =
        BugScreenSDK.log(LogLevel.INFO, "trip_opened: $id")
}
