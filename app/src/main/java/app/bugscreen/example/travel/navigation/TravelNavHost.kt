package app.bugscreen.example.travel.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.bugscreen.example.travel.BuildConfig
import app.bugscreen.example.travel.ui.components.WelcomeDialog
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.bugscreen.example.travel.TravelViewModel
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.ui.detail.DestinationDetailScreen
import app.bugscreen.example.travel.ui.explore.ExploreScreen
import app.bugscreen.example.travel.ui.profile.ProfileScreen
import app.bugscreen.example.travel.ui.saved.SavedScreen
import app.bugscreen.example.travel.ui.trips.TripDetailScreen
import app.bugscreen.example.travel.ui.trips.TripsScreen

/** Root of the app: a bottom-nav scaffold hosting the four tabs plus the detail screens. */
@Composable
fun TravelNavHost() {
    // One ViewModel scoped to the whole app, shared across every screen.
    val viewModel: TravelViewModel = viewModel()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in topLevelRoutes

    // Show an intro on each launch: what this sample is, and that a screenshot files a bug report.
    // rememberSaveable keeps it dismissed across config changes (rotation) within the same launch.
    var showWelcome by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
            ) {
                NavigationBar {
                    TopLevelDestination.entries.forEach { tab ->
                        val selected = currentRoute == tab.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                    contentDescription = tab.label,
                                )
                            },
                            label = { Text(tab.label) },
                        )
                    }
                }
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.EXPLORE,
            modifier = Modifier.padding(padding),
        ) {
            composable(Routes.EXPLORE) {
                ExploreScreen(
                    viewModel = viewModel,
                    onDestinationClick = { id ->
                        BugScreenBreadcrumbs.destinationOpened(id)
                        navController.navigate(Routes.destinationDetail(id))
                    },
                )
            }
            composable(Routes.SAVED) {
                SavedScreen(
                    viewModel = viewModel,
                    onDestinationClick = { id ->
                        BugScreenBreadcrumbs.destinationOpened(id)
                        navController.navigate(Routes.destinationDetail(id))
                    },
                    onExplore = { navController.navigate(Routes.EXPLORE) },
                )
            }
            composable(Routes.TRIPS) {
                TripsScreen(
                    viewModel = viewModel,
                    onTripClick = { id ->
                        BugScreenBreadcrumbs.tripOpened(id)
                        navController.navigate(Routes.tripDetail(id))
                    },
                )
            }
            composable(Routes.PROFILE) {
                ProfileScreen(viewModel = viewModel)
            }
            composable(Routes.DESTINATION_DETAIL) { entry ->
                val id = entry.arguments?.getString("destinationId").orEmpty()
                DestinationDetailScreen(
                    destinationId = id,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }
            composable(Routes.TRIP_DETAIL) { entry ->
                val id = entry.arguments?.getString("tripId").orEmpty()
                TripDetailScreen(
                    tripId = id,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }

    if (showWelcome) {
        WelcomeDialog(
            apiKeyConfigured = BuildConfig.BUGSCREEN_API_KEY.isNotBlank(),
            onDismiss = { showWelcome = false },
        )
    }
}
