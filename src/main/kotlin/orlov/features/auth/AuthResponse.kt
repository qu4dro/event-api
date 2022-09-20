package orlov.features.auth

import kotlinx.serialization.Serializable
import orlov.data.models.UserInfo

@Serializable
data class LoginResponse(
    val token: String,
    val userInfo: UserInfo
)

@Serializable
data class RegisterResponse(
    val userInfo: UserInfo
)