package orlov.features.events.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchUserEventsRequest(
    val userLogin: String
)