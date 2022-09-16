package orlov.plugins

import io.ktor.server.application.*
import orlov.features.auth.routes.authRoutes

fun Application.configureRouting() {
    authRoutes()
}
