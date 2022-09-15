package orlov.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import orlov.features.user.models.AuthRequest
import java.util.*

class JwtTokenService() : TokenService {

    private val expiresIn = 365L * 1000L * 60L * 24L
    private val secret = System.getenv("JWT_SECRET")
    override fun generate(authRequest: AuthRequest): String {
        var token = JWT.create()
            .withSubject("Authentication")
            .withClaim("username", authRequest.username)
            .withExpiresAt(Date(System.currentTimeMillis() + expiresIn))
            .withClaim("name", authRequest.username)

        return token.sign(Algorithm.HMAC256(secret))
    }
}

