package orlov.features.events.models

import kotlinx.serialization.Serializable
import orlov.data.events.EventDTO

@Serializable
data class FetchUserEventsResponse(
    val events: List<EventDTO>
)