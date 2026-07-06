package app.bugscreen.example.travel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bugscreen.example.travel.bugscreen.BugScreenBreadcrumbs
import app.bugscreen.example.travel.data.TravelRepository
import app.bugscreen.example.travel.data.model.Destination
import app.bugscreen.example.travel.data.model.Trip
import app.bugscreen.example.travel.data.model.UserProfile
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Single app-scoped ViewModel bridging [TravelRepository] to the Compose UI. Also the place
 * where user actions turn into BugScreen breadcrumbs, so screen composables never call the
 * SDK for logging themselves.
 */
class TravelViewModel : ViewModel() {

    val destinations: List<Destination> = TravelRepository.destinations
    val trips: List<Trip> = TravelRepository.trips
    val currentUser: UserProfile = TravelRepository.currentUser

    val savedIds: StateFlow<Set<String>> = TravelRepository.savedIds

    val savedDestinations: StateFlow<List<Destination>> =
        savedIds
            .map { ids -> destinations.filter { it.id in ids } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun destination(id: String): Destination? = TravelRepository.destination(id)

    fun trip(id: String): Trip? = TravelRepository.trip(id)

    fun toggleSaved(destination: Destination) {
        val nowSaved = !TravelRepository.isSaved(destination.id)
        TravelRepository.toggleSaved(destination.id)
        BugScreenBreadcrumbs.saveToggled(destination.id, nowSaved)
    }
}
