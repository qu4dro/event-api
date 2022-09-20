package orlov.features.events

import kotlinx.serialization.Serializable

@Serializable
data class CreateEventRequest(
    val name: String,
    val description: String,
    val dateTime: Long,
    val lng: String,
    val lat: String
)

@Serializable
data class FetchEventRequest(
    val eventId: Int
)

@Serializable
data class FetchUserEventsRequest(
    val userLogin: String
)

@Serializable
data class FetchNearbyEventsRequest(
    val radius: Int
)
