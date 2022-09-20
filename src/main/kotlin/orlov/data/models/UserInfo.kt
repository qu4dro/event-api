package orlov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val login: String,
    val username: String
)