package app.bugscreen.example.travel.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

/** All navigation routes in one place. */
object Routes {
    const val EXPLORE = "explore"
    const val SAVED = "saved"
    const val TRIPS = "trips"
    const val PROFILE = "profile"

    const val DESTINATION_DETAIL = "destination/{destinationId}"
    const val TRIP_DETAIL = "trip/{tripId}"

    fun destinationDetail(id: String) = "destination/$id"
    fun tripDetail(id: String) = "trip/$id"
}

/** A bottom-navigation tab. */
enum class TopLevelDestination(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    EXPLORE(Routes.EXPLORE, "Explore", Icons.Filled.Explore, Icons.Outlined.Explore),
    SAVED(Routes.SAVED, "Saved", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    TRIPS(Routes.TRIPS, "Trips", Icons.Filled.Map, Icons.Outlined.Map),
    PROFILE(Routes.PROFILE, "Profile", Icons.Filled.Person, Icons.Outlined.Person),
}

val topLevelRoutes: Set<String> = TopLevelDestination.entries.map { it.route }.toSet()
