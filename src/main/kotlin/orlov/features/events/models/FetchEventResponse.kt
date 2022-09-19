package orlov.features.events.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchEventResponse(
    val name: String,
    val description: String,
    val dateTime: Long,
    val creator: String,
    val lng: String,
    val lat: String
)
