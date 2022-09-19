package orlov.features.events.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchEventRequest(
    val eventId: Int
)