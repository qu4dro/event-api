package orlov.features.user.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent
import orlov.features.user.models.AuthRequest
import orlov.features.user.models.UserDto
import orlov.features.user.repository.UserRepository
import orlov.security.hashing.HashingService

fun Route.signUp() {

    val repository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)
    val hashingService: HashingService by KoinJavaComponent.inject(HashingService::class.java)

    post("/api/v1/authentication/signup") {
        val request = call.receiveNullable<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
        val isPwTooShort = request.password.length < 8

        if (areFieldsBlank || isPwTooShort) {
            call.respond(HttpStatusCode.Conflict, "Fields are blank or password is too short")
            return@post
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)
        val user = UserDto(
            username = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        val wasAcknowledged = repository.insertUser(user)

        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        call.respond(HttpStatusCode.OK)

    }
}