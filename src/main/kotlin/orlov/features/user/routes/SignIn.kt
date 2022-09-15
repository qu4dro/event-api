package orlov.features.user.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import orlov.features.user.models.AuthRequest
import orlov.features.user.models.AuthResponse
import orlov.features.user.models.UserInfo
import orlov.features.user.repository.UserRepository
import orlov.security.hashing.HashingService
import orlov.security.hashing.SaltedHash
import orlov.security.token.TokenService

fun Route.signIn(
) {

    val repository: UserRepository by inject(UserRepository::class.java)
    val hashingService: HashingService by inject(HashingService::class.java)
    val tokenService: TokenService by inject(TokenService::class.java)

    post("/api/v1/authentication/signin") {
        val request = call.receiveNullable<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = repository.getUserByUserName(request.username)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return@post
        }

        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = user.password,
                salt = user.salt
            )
        )

        if (!isValidPassword) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
        }

        val token = tokenService.generate(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = AuthResponse(
                token = token,
                UserInfo(
                    username = user.username,
                    id = user.id.toString()
                )
            )
        )
    }
}