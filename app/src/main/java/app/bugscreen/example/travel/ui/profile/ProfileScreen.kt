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
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.bugscreen.android.BugScreenSDK
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

        // --- Help: the one manual BugScreen entry point ---
        SettingsGroup(title = "Help") {
            // Opens the BugScreen report screen on demand. Everything else is automatic —
            // taking a screenshot anywhere in the app opens this same screen by itself.
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

        SettingsGroup(title = "About") {
            SettingsRow(
                icon = Icons.Outlined.Info,
                title = "BugScreen Travel",
                subtitle = "Version 1.0 · BugScreen example app",
            )
        }
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
