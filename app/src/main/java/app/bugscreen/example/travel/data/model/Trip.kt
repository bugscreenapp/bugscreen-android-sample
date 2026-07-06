package app.bugscreen.example.travel.data.model

/** A planned itinerary made up of day-by-day stops. */
data class Trip(
    val id: String,
    val title: String,
    val subtitle: String,
    val summary: String,
    val destinationIds: List<String>,
    val days: List<TripDay>,
)

data class TripDay(
    val label: String,
    val title: String,
    val items: List<String>,
)
