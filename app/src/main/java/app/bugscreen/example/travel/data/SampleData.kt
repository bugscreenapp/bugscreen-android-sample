package app.bugscreen.example.travel.data

import app.bugscreen.example.travel.data.model.Destination
import app.bugscreen.example.travel.data.model.DestinationKind
import app.bugscreen.example.travel.data.model.Trip
import app.bugscreen.example.travel.data.model.TripDay
import app.bugscreen.example.travel.data.model.UserProfile

/** Bundled, static content for the example. No network, no database. */
object SampleData {

    val currentUser = UserProfile(
        userId = "usr_84213",
        displayName = "Ava Rivera",
        email = "ava.rivera@example.com",
        plan = "explorer_pro",
        homeCity = "Lisbon",
        memberSince = "2022",
        tripsPlanned = 12,
        countriesVisited = 27,
    )

    val destinations: List<Destination> = listOf(
        Destination(
            id = "kyoto",
            name = "Kyoto",
            country = "Japan",
            kind = DestinationKind.City,
            blurb = "Temples, tea houses and quiet bamboo groves.",
            description = "Japan's former capital trades neon for nuance — a thousand shrines, " +
                "moss gardens raked to perfection, and geisha districts that glow at dusk. " +
                "Spring cherry blossoms and autumn maples draw crowds, but a dawn walk through " +
                "Arashiyama still feels like a secret.",
            highlights = listOf("Fushimi Inari", "Arashiyama Bamboo", "Gion District", "Kinkaku-ji"),
            bestSeason = "Mar–Apr · Nov",
            rating = 4.9,
        ),
        Destination(
            id = "amalfi",
            name = "Amalfi Coast",
            country = "Italy",
            kind = DestinationKind.Beach,
            blurb = "Pastel villages stacked above a cobalt sea.",
            description = "Fifty kilometres of cliffside drama where lemon groves cling to the " +
                "hills and ceramic-tiled domes catch the light. Ferry between Positano, Amalfi " +
                "and Ravello, then find a hidden cove for an afternoon swim.",
            highlights = listOf("Positano", "Path of the Gods", "Ravello gardens", "Limoncello tasting"),
            bestSeason = "May–Jun · Sep",
            rating = 4.8,
        ),
        Destination(
            id = "patagonia",
            name = "Torres del Paine",
            country = "Chile",
            kind = DestinationKind.Mountain,
            blurb = "Granite spires, glaciers and roaming guanacos.",
            description = "Patagonia at its most theatrical: wind-scoured steppe giving way to " +
                "turquoise lakes and the three sheer towers that give the park its name. The " +
                "W trek strings together the highlights over four unforgettable days.",
            highlights = listOf("Base of the Towers", "Grey Glacier", "French Valley", "Lake Pehoé"),
            bestSeason = "Nov–Mar",
            rating = 4.9,
        ),
        Destination(
            id = "santorini",
            name = "Santorini",
            country = "Greece",
            kind = DestinationKind.Island,
            blurb = "Whitewashed caldera towns and famous sunsets.",
            description = "A crescent of volcanic cliffs ringed by the Aegean. Blue-domed churches, " +
                "black-sand beaches and Oia's legendary golden hour make this the postcard of the " +
                "Cyclades — quieter and just as lovely one village over in Pyrgos.",
            highlights = listOf("Oia sunset", "Red Beach", "Ancient Akrotiri", "Caldera cruise"),
            bestSeason = "May–Jun · Sep–Oct",
            rating = 4.7,
        ),
        Destination(
            id = "banff",
            name = "Banff",
            country = "Canada",
            kind = DestinationKind.Lake,
            blurb = "Glacier-fed lakes ringed by the Rockies.",
            description = "The Canadian Rockies distilled: impossibly blue Lake Louise and Moraine " +
                "Lake, hot springs to soak tired legs, and a walkable town where elk wander the " +
                "streets at dusk. Endless trails fan out from every trailhead.",
            highlights = listOf("Moraine Lake", "Lake Louise", "Johnston Canyon", "Banff Gondola"),
            bestSeason = "Jun–Sep",
            rating = 4.8,
        ),
        Destination(
            id = "marrakech",
            name = "Marrakech",
            country = "Morocco",
            kind = DestinationKind.Desert,
            blurb = "A maze of souks on the edge of the Sahara.",
            description = "The Red City hums — spice-scented souks, hidden riad courtyards and the " +
                "nightly theatre of Jemaa el-Fnaa. Escape the medina for the calm of the Majorelle " +
                "Garden, or ride south toward the dunes of the desert.",
            highlights = listOf("Jemaa el-Fnaa", "Majorelle Garden", "Bahia Palace", "Atlas day trip"),
            bestSeason = "Mar–May · Oct–Nov",
            rating = 4.6,
        ),
        Destination(
            id = "blackforest",
            name = "Black Forest",
            country = "Germany",
            kind = DestinationKind.Forest,
            blurb = "Dense pine valleys, cuckoo clocks and spa towns.",
            description = "Fairytale country of dark wooded hills, half-timbered villages and " +
                "waterfalls tucked into the folds. Drive the scenic Schwarzwaldhochstraße, then " +
                "reward yourself with a slice of the region's namesake cherry cake.",
            highlights = listOf("Triberg Falls", "Titisee lake", "Baden-Baden spa", "Scenic road"),
            bestSeason = "May–Sep",
            rating = 4.5,
        ),
        Destination(
            id = "tuscany",
            name = "Val d'Orcia",
            country = "Italy",
            kind = DestinationKind.Countryside,
            blurb = "Rolling vineyards and cypress-lined roads.",
            description = "The Tuscany of the imagination: golden hills combed with vineyards, lone " +
                "cypress trees on the ridgelines, and medieval hill towns serving Brunello by the " +
                "glass. Slow mornings, long lunches, softer light.",
            highlights = listOf("Pienza", "Montalcino wine", "Bagno Vignoni", "Cypress road"),
            bestSeason = "Apr–Jun · Sep",
            rating = 4.7,
        ),
    )

    val trips: List<Trip> = listOf(
        Trip(
            id = "trip_japan",
            title = "Classic Japan",
            subtitle = "8 days · Kyoto & around",
            summary = "Temples, gardens and slow mornings across Japan's cultural heart.",
            destinationIds = listOf("kyoto"),
            days = listOf(
                TripDay("Day 1", "Arrive in Kyoto", listOf(
                    "Check in near Gion",
                    "Evening stroll along the Kamo River",
                    "Kaiseki dinner",
                )),
                TripDay("Day 2", "Southern Higashiyama", listOf(
                    "Fushimi Inari at dawn",
                    "Tofuku-ji gardens",
                    "Tea ceremony in the afternoon",
                )),
                TripDay("Day 3", "Arashiyama", listOf(
                    "Bamboo grove before the crowds",
                    "Okochi-Sanso villa",
                    "Riverside lunch",
                )),
                TripDay("Day 4", "Golden Pavilion", listOf(
                    "Kinkaku-ji",
                    "Ryoan-ji rock garden",
                    "Nishiki Market for dinner",
                )),
            ),
        ),
        Trip(
            id = "trip_med",
            title = "Mediterranean Escape",
            subtitle = "10 days · Italy & Greece",
            summary = "Cliffside villages, ferries and sunsets from the Amalfi Coast to Santorini.",
            destinationIds = listOf("amalfi", "santorini"),
            days = listOf(
                TripDay("Day 1–2", "Positano", listOf(
                    "Settle into the cliffside",
                    "Beach club afternoon",
                    "Sunset aperitivo",
                )),
                TripDay("Day 3", "Path of the Gods", listOf(
                    "Morning hike to Nocelle",
                    "Ferry to Amalfi town",
                    "Ravello gardens at golden hour",
                )),
                TripDay("Day 4–5", "Fly to Santorini", listOf(
                    "Caldera-view hotel in Oia",
                    "Sunset point before dinner",
                )),
                TripDay("Day 6", "Island day", listOf(
                    "Catamaran around the caldera",
                    "Swim at the hot springs",
                    "Wine tasting in Pyrgos",
                )),
            ),
        ),
        Trip(
            id = "trip_patagonia",
            title = "Patagonia Trek",
            subtitle = "6 days · The W Circuit",
            summary = "Four days on the trail through Torres del Paine, bookended by Puerto Natales.",
            destinationIds = listOf("patagonia"),
            days = listOf(
                TripDay("Day 1", "Puerto Natales", listOf(
                    "Gear check and briefing",
                    "Provision for the trail",
                )),
                TripDay("Day 2", "Base of the Towers", listOf(
                    "Steep climb to the lookout",
                    "Overnight at Refugio Chileno",
                )),
                TripDay("Day 3", "French Valley", listOf(
                    "Hanging glaciers overhead",
                    "Camp at Los Cuernos",
                )),
                TripDay("Day 4", "Grey Glacier", listOf(
                    "Ice viewpoints along the lake",
                    "Optional kayak among the bergs",
                )),
            ),
        ),
    )
}
