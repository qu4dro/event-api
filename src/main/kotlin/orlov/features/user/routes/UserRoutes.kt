package orlov.features.user.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.userRoutes() {
    routing {
        signIn()
        signUp()
        authenticate()
        getSecretInfo()
    }
}