package orlov.data.users

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val login: String,
    val username: String
)