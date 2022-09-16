package orlov.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*

fun Application.configureSecurity() {
    
    authentication {
            jwt {
                realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(System.getenv("JWT_SECRET")))
                        .build()
                )
                validate { credential ->
//                    if (credential.payload.audience.contains(config.audience)) {
                        JWTPrincipal(credential.payload)
//                    } else null
                }
            }
        }

}
