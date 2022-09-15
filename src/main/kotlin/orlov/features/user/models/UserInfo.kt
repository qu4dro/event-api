package orlov.features.user.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val username: String,
    val id: String
)