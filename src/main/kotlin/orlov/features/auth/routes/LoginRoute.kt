package orlov.features.auth.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent
import orlov.data.users.UserInfo
import orlov.data.users.UsersService
import orlov.features.auth.models.LoginRequest
import orlov.features.auth.models.LoginResponse
import orlov.security.hashing.HashingService
import orlov.security.hashing.SaltedHash
import orlov.security.token.TokenService

fun Route.login() {

    val hashingService: HashingService by KoinJavaComponent.inject(HashingService::class.java)
    val tokenService: TokenService by KoinJavaComponent.inject(TokenService::class.java)
    val usersService: UsersService by KoinJavaComponent.inject(UsersService::class.java)

    post("/api/v1/authentication/signin") {
        val request = call.receiveNullable<LoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = usersService.fetchUser(request.login)
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
            return@post
        }

        val token = tokenService.generate(request.login)

        call.respond(
            status = HttpStatusCode.OK,
            message = LoginResponse(
                token = token,
                UserInfo(
                    username = user.username,
                    login = user.login
                )
            )
        )
    }
}