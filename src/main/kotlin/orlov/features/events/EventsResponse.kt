package orlov.features.events

import kotlinx.serialization.Serializable
import orlov.data.models.EventDTO

@Serializable
data class FetchUserEventsResponse(
    val events: List<EventDTO>
)

@Serializable
data class FetchEventResponse(
    val name: String,
    val description: String,
    val dateTime: Long,
    val creator: String,
    val lng: String,
    val lat: String
)