package orlov.features.auth.models

import kotlinx.serialization.Serializable
import orlov.data.users.UserInfo

@Serializable
data class LoginResponse(
    val token: String,
    val userInfo: UserInfo
)