package app.bugscreen.example.travel.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bugscreen.example.travel.TravelViewModel
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.ui.components.HeroImage
import app.bugscreen.example.travel.ui.components.HighlightChip
import app.bugscreen.example.travel.ui.components.RatingPill
import app.bugscreen.example.travel.ui.components.SaveButton
import app.bugscreen.example.travel.ui.components.SectionHeader

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DestinationDetailScreen(
    destinationId: String,
    viewModel: TravelViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(destinationId) { BugScreenBreadcrumbs.screenViewed("destination_detail") }

    val destination = viewModel.destination(destinationId)
    if (destination == null) {
        MissingContent(onBack)
        return
    }

    val savedIds by viewModel.savedIds.collectAsStateWithLifecycle()
    val saved = destination.id in savedIds
    val topInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box {
            HeroImage(
                kind = destination.kind,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                ) {
                    RatingPill(rating = destination.rating)
                    Text(
                        text = destination.name,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Place,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.height(18.dp),
                        )
                        Text(
                            text = destination.country,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }

            // Overlaid top controls, offset below the status bar.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = topInset + 8.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Surface(
                    onClick = onBack,
                    shape = CircleShape,
                    color = Color.Black.copy(alpha = 0.32f),
                    modifier = Modifier.height(40.dp),
                ) {
                    Box(modifier = Modifier.height(40.dp).padding(horizontal = 9.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                        )
                    }
                }
                SaveButton(saved = saved, onClick = { viewModel.toggleSaved(destination) })
            }
        }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "Best time to visit · ${destination.bestSeason}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Text(
                text = destination.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(title = "Highlights")
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    destination.highlights.forEach { HighlightChip(text = it) }
                }
            }
        }
    }
}

@Composable
private fun MissingContent(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Destination not found", style = MaterialTheme.typography.titleLarge)
        Surface(onClick = onBack, modifier = Modifier.padding(top = 12.dp)) {
            Text("Go back", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }
    }
}
