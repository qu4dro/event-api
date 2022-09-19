package orlov.features.auth.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.authRoutes() {
    routing {
        login()
        register()
        authenticate()
    }
}