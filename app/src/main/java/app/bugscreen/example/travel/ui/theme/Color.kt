package app.bugscreen.example.travel.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// A warm, travel-inspired palette: ocean teal + sunset coral over a sandy neutral.
// Dynamic color is intentionally left off (see Theme.kt) so this brand identity shows
// consistently in screenshots and marketing.

private val Teal = Color(0xFF0F766E)
private val TealLightContainer = Color(0xFFA7F3E9)
private val Coral = Color(0xFFE05D3C)
private val CoralLightContainer = Color(0xFFFFDBCF)
private val Sand = Color(0xFFC98A20)
private val SandLightContainer = Color(0xFFFCE6BF)

internal val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = TealLightContainer,
    onPrimaryContainer = Color(0xFF00201C),
    secondary = Coral,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = CoralLightContainer,
    onSecondaryContainer = Color(0xFF3A0B00),
    tertiary = Sand,
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = SandLightContainer,
    onTertiaryContainer = Color(0xFF291800),
    background = Color(0xFFFBF8F3),
    onBackground = Color(0xFF1B1C18),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1C18),
    surfaceVariant = Color(0xFFECE6DC),
    onSurfaceVariant = Color(0xFF4B4740),
    outline = Color(0xFF7C786F),
    outlineVariant = Color(0xFFD5CFC4),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
)

internal val DarkColors = darkColorScheme(
    primary = Color(0xFF6FD8CC),
    onPrimary = Color(0xFF003731),
    primaryContainer = Color(0xFF005047),
    onPrimaryContainer = Color(0xFFA7F3E9),
    secondary = Color(0xFFFFB59D),
    onSecondary = Color(0xFF5C1900),
    secondaryContainer = Color(0xFF7C2D12),
    onSecondaryContainer = Color(0xFFFFDBCF),
    tertiary = Color(0xFFEEC169),
    onTertiary = Color(0xFF442B00),
    tertiaryContainer = Color(0xFF614000),
    onTertiaryContainer = Color(0xFFFCE6BF),
    background = Color(0xFF0F1311),
    onBackground = Color(0xFFE3E3DC),
    surface = Color(0xFF171B18),
    onSurface = Color(0xFFE3E3DC),
    surfaceVariant = Color(0xFF43483F),
    onSurfaceVariant = Color(0xFFC7C7BB),
    outline = Color(0xFF8D9286),
    outlineVariant = Color(0xFF43483F),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
)
