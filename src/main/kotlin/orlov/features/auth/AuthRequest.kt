package orlov.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val password: String,
    val login: String
)


@Serializable
data class RegisterRequest(
    val password: String,
    val login: String,
    val username: String
)