package app.bugscreen.example.travel.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.bugscreen.example.travel.TravelViewModel
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.data.model.Destination
import app.bugscreen.example.travel.ui.components.HeroImage
import app.bugscreen.example.travel.ui.components.SaveButton

@Composable
fun SavedScreen(
    viewModel: TravelViewModel,
    onDestinationClick: (String) -> Unit,
    onExplore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) { BugScreenBreadcrumbs.screenViewed("saved") }

    val saved by viewModel.savedDestinations.collectAsStateWithLifecycle()

    if (saved.isEmpty()) {
        EmptySaved(onExplore = onExplore, modifier = modifier)
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Saved",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 4.dp),
            )
        }
        items(saved, key = { it.id }) { destination ->
            SavedCard(
                destination = destination,
                onClick = { onDestinationClick(destination.id) },
                onToggleSave = { viewModel.toggleSaved(destination) },
            )
        }
    }
}

@Composable
private fun SavedCard(
    destination: Destination,
    onClick: () -> Unit,
    onToggleSave: () -> Unit,
) {
    Card(onClick = onClick, shape = RoundedCornerShape(20.dp)) {
        HeroImage(
            kind = destination.kind,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
                        ),
                    ),
            )
            SaveButton(
                saved = true,
                onClick = onToggleSave,
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
            ) {
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = destination.country,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.85f),
                )
            }
        }
    }
}

@Composable
private fun EmptySaved(onExplore: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(56.dp),
        )
        Text(
            text = "Nothing saved yet",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp),
        )
        Text(
            text = "Tap the heart on a place to keep it here for later.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp),
        )
        Button(onClick = onExplore, modifier = Modifier.padding(top = 20.dp)) {
            Text("Start exploring")
        }
    }
}
