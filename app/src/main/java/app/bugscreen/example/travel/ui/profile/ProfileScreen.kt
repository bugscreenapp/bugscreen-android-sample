package app.bugscreen.example.travel.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material.icons.outlined.StopCircle
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.bugscreen.android.BugScreenSDK
import app.bugscreen.android.LogLevel
import app.bugscreen.example.travel.BuildConfig
import app.bugscreen.example.travel.TravelViewModel
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.ui.components.StatItem

@Composable
fun ProfileScreen(
    viewModel: TravelViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) { BugScreenBreadcrumbs.screenViewed("profile") }

    val context = LocalContext.current
    val user = viewModel.currentUser

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        // --- Account header ---
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(84.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = user.displayName.split(" ").mapNotNull { it.firstOrNull() }.take(2).joinToString(""),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Text(
                text = user.displayName,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            PlanBadge(plan = user.plan, modifier = Modifier.padding(top = 8.dp))
        }

        // --- Stats ---
        Card(shape = RoundedCornerShape(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                StatItem(value = user.tripsPlanned.toString(), label = "Trips")
                StatItem(value = user.countriesVisited.toString(), label = "Countries")
                StatItem(value = user.memberSince, label = "Member since")
            }
        }

        // --- Settings (inert in this example) ---
        SettingsGroup(title = "Settings") {
            SettingsRow(Icons.Outlined.Notifications, "Notifications", "Trip reminders and deals")
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SettingsRow(Icons.Outlined.DarkMode, "Appearance", "Follows your system theme")
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SettingsRow(Icons.Outlined.Lock, "Privacy", "Manage your data")
        }

        SettingsGroup(title = "Help") {
            // (5) Open the reporter directly from the app's Help section.
            SettingsRow(
                icon = Icons.Outlined.BugReport,
                title = "Report a problem",
                subtitle = "Opens the BugScreen report form",
                onClick = { BugScreenSDK.openBugReportScreen(context) },
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SettingsRow(
                icon = Icons.Outlined.ChatBubbleOutline,
                title = "Send feedback",
                subtitle = "Tell us what you think",
                onClick = { BugScreenSDK.openBugReportScreen(context) },
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SettingsRow(
                icon = Icons.Outlined.PhotoCamera,
                title = "Tip: take a screenshot",
                subtitle = "Screenshot anywhere to report a bug instantly",
            )
        }

        if (BuildConfig.DEBUG) {
            CaptureControls()
        }

        SettingsGroup(title = "About") {
            SettingsRow(
                icon = Icons.Outlined.Info,
                title = "BugScreen Travel",
                subtitle = "Version 1.0 · BugScreen example app",
            )
        }
    }
}

// (6) Call the published SDK APIs from the Profile screen.
@Composable
private fun CaptureControls() {
    var recordingStatus by remember { mutableStateOf<String?>(null) }
    val divider = MaterialTheme.colorScheme.outlineVariant

    SettingsGroup(title = "BugScreen capture controls") {
        SettingsRow(Icons.Outlined.BugReport, "Show floating button", "Open the SDK menu from any screen") {
            BugScreenSDK.showFloatingButton()
            BugScreenSDK.log(LogLevel.INFO, "floating_button_visible: true")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.BugReport, "Hide floating button", "Remove the floating menu") {
            BugScreenSDK.hideFloatingButton()
            BugScreenSDK.log(LogLevel.INFO, "floating_button_visible: false")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Videocam, "Start recent recording", "Keep up to 15 seconds after system consent") {
            BugScreenSDK.startScreenRecording()
            BugScreenSDK.log(LogLevel.INFO, "recording_requested: true")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.StopCircle, "Stop recent recording", "Stop capture and clear recent history") {
            BugScreenSDK.stopScreenRecording()
            BugScreenSDK.log(LogLevel.INFO, "recording_requested: false")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Info, "Check recording status", "Read the current state from the SDK") {
            val state = BugScreenSDK.screenRecordingState
            val status = state.status.name.lowercase().replace('_', ' ')
            val reason = state.reason?.name?.lowercase()?.replace('_', ' ')
            recordingStatus = if (reason == null) status else "$status ($reason)"
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Notifications, "Show report notification", "Allow notifications when asked") {
            BugScreenSDK.showReportNotification()
            BugScreenSDK.log(LogLevel.INFO, "report_notification_requested: true")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Notifications, "Hide report notification", "Remove the reporting shortcut") {
            BugScreenSDK.hideReportNotification()
            BugScreenSDK.log(LogLevel.INFO, "report_notification_requested: false")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Smartphone, "Enable shake-to-report", "Shake while the app is open to report") {
            BugScreenSDK.setShakeDetectionEnabled(true)
            BugScreenSDK.log(LogLevel.INFO, "shake_detection_enabled: true")
        }
        HorizontalDivider(color = divider)
        SettingsRow(Icons.Outlined.Smartphone, "Disable shake-to-report", "Turn off the motion shortcut") {
            BugScreenSDK.setShakeDetectionEnabled(false)
            BugScreenSDK.log(LogLevel.INFO, "shake_detection_enabled: false")
        }
    }

    recordingStatus?.let { status ->
        AlertDialog(
            onDismissRequest = { recordingStatus = null },
            title = { Text("Recording status") },
            text = { Text(status) },
            confirmButton = { TextButton(onClick = { recordingStatus = null }) { Text("OK") } },
        )
    }
}

@Composable
private fun PlanBadge(plan: String, modifier: Modifier = Modifier) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier,
    ) {
        Text(
            text = plan.replace('_', ' ').uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
        )
    }
}

@Composable
private fun SettingsGroup(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
        )
        Card(shape = RoundedCornerShape(20.dp)) {
            Column { content() }
        }
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: (() -> Unit)? = null,
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
    Row(
        modifier = rowModifier.padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
