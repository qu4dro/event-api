package orlov.features.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val password: String,
    val login: String,
    val username: String
)