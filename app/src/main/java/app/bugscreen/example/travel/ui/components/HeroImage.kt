package app.bugscreen.example.travel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BeachAccess
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.Grass
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Sailing
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material.icons.outlined.Water
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import app.bugscreen.example.travel.data.model.DestinationKind

/** Accent colors + glyph used to render a destination's procedural hero art. */
data class KindStyle(
    val start: Color,
    val end: Color,
    val icon: ImageVector,
)

fun kindStyle(kind: DestinationKind): KindStyle = when (kind) {
    DestinationKind.Beach -> KindStyle(Color(0xFF16A3B0), Color(0xFF0E7490), Icons.Outlined.BeachAccess)
    DestinationKind.Island -> KindStyle(Color(0xFF0EA5A5), Color(0xFF2563A6), Icons.Outlined.Sailing)
    DestinationKind.Mountain -> KindStyle(Color(0xFF4B6CB7), Color(0xFF182848), Icons.Outlined.Terrain)
    DestinationKind.City -> KindStyle(Color(0xFF7B2A86), Color(0xFFB5307E), Icons.Outlined.LocationCity)
    DestinationKind.Forest -> KindStyle(Color(0xFF2E7D5B), Color(0xFF14532D), Icons.Outlined.Forest)
    DestinationKind.Desert -> KindStyle(Color(0xFFF2683C), Color(0xFFC2410C), Icons.Outlined.WbSunny)
    DestinationKind.Lake -> KindStyle(Color(0xFF1D8FA8), Color(0xFF0F5C7A), Icons.Outlined.Water)
    DestinationKind.Countryside -> KindStyle(Color(0xFF8FA33A), Color(0xFFB8860B), Icons.Outlined.Grass)
}

/**
 * A gradient "photo" for a destination, generated entirely from its [kind] — no bundled
 * image assets. A large, faint glyph in the corner hints at the type of place. Overlay
 * captions or controls via [content]; the caller is responsible for clipping to a shape.
 */
@Composable
fun HeroImage(
    kind: DestinationKind,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {},
) {
    val style = kindStyle(kind)
    Box(
        modifier = modifier.background(
            Brush.linearGradient(colors = listOf(style.start, style.end)),
        ),
    ) {
        androidx.compose.material3.Icon(
            imageVector = style.icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.16f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 28.dp, y = 28.dp)
                .size(180.dp),
        )
        content()
    }
}
