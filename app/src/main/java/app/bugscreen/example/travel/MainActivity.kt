package app.bugscreen.example.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.bugscreen.android.BugScreenSDK
import app.bugscreen.android.LogLevel
import app.bugscreen.example.travel.navigation.TravelNavHost
import app.bugscreen.example.travel.ui.theme.TravelTheme

/**
 * The single Activity hosting all Compose UI.
 *
 * Note it extends [ComponentActivity] — that's what BugScreenSDK.requestScreenshotPermission()
 * needs to drive the runtime permission flow on Android 13 and below.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // (2) Ask for the screenshot-detection permission when it's actually needed.
        //     No-op on Android 14+ (silent detection) or if it's already granted.
        //     You can skip this manual request entirely by calling
        //     .requestPermissionsOnStart(true) when you configure the SDK — BugScreen
        //     then prompts automatically on launch.
        if (BugScreenSDK.shouldRequestPermission(this)) {
            BugScreenSDK.requestScreenshotPermission(this) { granted ->
                BugScreenSDK.log(LogLevel.INFO, "screenshot permission granted=$granted")
            }
        }

        setContent {
            TravelTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TravelNavHost()
                }
            }
        }
    }
}
