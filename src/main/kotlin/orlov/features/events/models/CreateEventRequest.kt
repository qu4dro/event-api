package orlov.features.events.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateEventRequest(
    val name: String,
    val description: String,
    val dateTime: Long,
    val lng: String,
    val lat: String
)