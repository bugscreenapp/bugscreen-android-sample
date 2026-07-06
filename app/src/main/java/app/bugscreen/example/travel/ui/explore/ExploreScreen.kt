package app.bugscreen.example.travel.ui.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bugscreen.example.travel.TravelViewModel
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.ui.components.DestinationCard

@Composable
fun ExploreScreen(
    viewModel: TravelViewModel,
    onDestinationClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) { BugScreenBreadcrumbs.screenViewed("explore") }

    val savedIds by viewModel.savedIds.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            Column {
                Text(
                    text = "Explore",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Where to next, ${viewModel.currentUser.displayName.substringBefore(' ')}?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
        items(viewModel.destinations, key = { it.id }) { destination ->
            DestinationCard(
                destination = destination,
                saved = destination.id in savedIds,
                onClick = { onDestinationClick(destination.id) },
                onToggleSave = { viewModel.toggleSaved(destination) },
            )
        }
    }
}
