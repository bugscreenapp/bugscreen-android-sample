package app.bugscreen.example.travel.data.model

/** The (mock) signed-in user. Some of these fields are attached to BugScreen reports as
 *  custom data — see TravelApp.registerSignedInUser(). */
data class UserProfile(
    val userId: String,
    val displayName: String,
    val email: String,
    val plan: String,
    val homeCity: String,
    val memberSince: String,
    val tripsPlanned: Int,
    val countriesVisited: Int,
)
