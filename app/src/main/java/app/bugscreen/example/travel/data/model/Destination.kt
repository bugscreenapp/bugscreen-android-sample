package app.bugscreen.example.travel.data.model

/**
 * A place shown in the app. Hero imagery is generated procedurally from [kind] (see
 * ui/components/HeroImage.kt), so the repo ships no photos and stays self-contained and
 * free of any image-licensing concerns.
 */
data class Destination(
    val id: String,
    val name: String,
    val country: String,
    val kind: DestinationKind,
    val blurb: String,
    val description: String,
    val highlights: List<String>,
    val bestSeason: String,
    val rating: Double,
)

/** Category of a destination — drives its accent colors and the glyph on its hero art. */
enum class DestinationKind {
    Beach,
    Island,
    Mountain,
    City,
    Forest,
    Desert,
    Lake,
    Countryside,
}
