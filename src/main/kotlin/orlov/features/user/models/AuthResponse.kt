package orlov.features.user.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val userInfo: UserInfo
)