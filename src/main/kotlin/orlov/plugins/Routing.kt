package orlov.plugins

import io.ktor.server.application.*
import orlov.features.auth.routes.authRoutes
import orlov.features.events.routes.eventRoutes

fun Application.configureRouting() {
    authRoutes()
    eventRoutes()
}
