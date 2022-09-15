package orlov.security.token

import orlov.features.user.models.AuthRequest

interface TokenService {
    fun generate(
        authRequest: AuthRequest
    ): String
}