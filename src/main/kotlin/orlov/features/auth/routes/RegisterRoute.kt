package orlov.features.auth.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.java.KoinJavaComponent
import orlov.data.models.UserDTO
import orlov.data.dao.UsersDao
import orlov.features.auth.RegisterRequest
import orlov.security.hashing.HashingService

fun Route.register() {

    val hashingService: HashingService by KoinJavaComponent.inject(HashingService::class.java)
    val usersDao: UsersDao by KoinJavaComponent.inject(UsersDao::class.java)

    post("/api/v1/authentication/signup") {
        val request = call.receiveNullable<RegisterRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val existingUser = usersDao.fetchUser(request.login)

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
                usersDao.insertUser(user)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Can not signup")
                return@post
            }
        }

        call.respond(HttpStatusCode.OK)

    }
}