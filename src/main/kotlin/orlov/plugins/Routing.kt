package orlov.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import orlov.authenticate
import orlov.data.user.UserDataSource
import orlov.getSecretInfo
import orlov.security.hashing.HashingService
import orlov.security.token.JwtTokenService
import orlov.security.token.TokenConfig
import orlov.signIn
import orlov.signUp

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: JwtTokenService,
    tokenConfig: TokenConfig
) {
    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
