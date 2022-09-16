package orlov.features.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val password: String,
    val login: String
)