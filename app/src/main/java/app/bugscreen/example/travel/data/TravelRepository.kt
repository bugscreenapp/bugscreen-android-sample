package app.bugscreen.example.travel.data

import app.bugscreen.example.travel.data.model.Destination
import app.bugscreen.example.travel.data.model.Trip
import app.bugscreen.example.travel.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * In-memory store over the bundled [SampleData]. Saved destinations live only for the
 * process lifetime — persistence isn't the point of this example, so there's no database.
 */
object TravelRepository {

    val destinations: List<Destination> = SampleData.destinations
    val trips: List<Trip> = SampleData.trips
    val currentUser: UserProfile = SampleData.currentUser

    private val _savedIds = MutableStateFlow(setOf("amalfi", "banff"))
    val savedIds: StateFlow<Set<String>> = _savedIds.asStateFlow()

    fun isSaved(id: String): Boolean = id in _savedIds.value

    fun toggleSaved(id: String) {
        _savedIds.update { current ->
            if (id in current) current - id else current + id
        }
    }

    fun destination(id: String): Destination? = destinations.firstOrNull { it.id == id }

    fun trip(id: String): Trip? = trips.firstOrNull { it.id == id }
}
