package orlov.features.auth.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.java.KoinJavaComponent
import orlov.data.users.UserDTO
import orlov.data.users.UsersService
import orlov.features.auth.models.RegisterRequest
import orlov.security.hashing.HashingService

fun Route.register() {

    val hashingService: HashingService by KoinJavaComponent.inject(HashingService::class.java)
    val usersService: UsersService by KoinJavaComponent.inject(UsersService::class.java)

    post("/api/v1/authentication/signup") {
        val request = call.receiveNullable<RegisterRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val existingUser = usersService.fetchUser(request.login)

        if (existingUser != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
            return@post
        } else {
            val saltedHash = hashingService.generateSaltedHash(request.password)
            val user = UserDTO(
                login = request.login,
                username = request.username,
                password = saltedHash.hash,
                salt = saltedHash.salt
            )
            try {
                usersService.insertUser(user)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Can not signup")
                return@post
            }
        }

        call.respond(HttpStatusCode.OK)

    }
}