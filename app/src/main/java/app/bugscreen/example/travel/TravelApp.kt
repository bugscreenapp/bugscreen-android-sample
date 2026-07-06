package app.bugscreen.example.travel

import android.app.Application
import app.bugscreen.android.BugScreenConfig
import app.bugscreen.android.BugScreenSDK
import app.bugscreen.example.travel.data.TravelRepository
import java.util.UUID

/**
 * Application entry point.
 *
 * This is where a real app initializes BugScreen — once, at startup. Because the build pairs
 * `app.bugscreen:android` on debug with `app.bugscreen:android-no-op` on release (see
 * app/build.gradle.kts), these exact same calls do nothing in a release build, so there's no
 * need to guard them behind a build-type check.
 */
class TravelApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // (1) Start the SDK. From here on, a screenshot taken anywhere in the app opens the
        //     bug-report screen automatically — no per-screen wiring required.
        BugScreenSDK.start(
            BugScreenConfig.Builder(this)
                .apiKey(BuildConfig.BUGSCREEN_API_KEY) // supplied via local.properties; "" is fine
                .screenshotDetection(true)
                .debug(BuildConfig.DEBUG)
                .requestPermissionsOnStart(true)
                .build(),
        )

        // (3) Attach who-and-what context so it rides along with every report the user files.
        registerSignedInUser()
    }

    /** Real apps attach the signed-in user and session so reports are actionable. */
    private fun registerSignedInUser() {
        val user = TravelRepository.currentUser
        BugScreenSDK.setCustomData(
            mapOf(
                "userId" to user.userId,
                "plan" to user.plan,
                "homeCity" to user.homeCity,
                "sessionId" to UUID.randomUUID().toString(),
            ),
        )
    }
}
