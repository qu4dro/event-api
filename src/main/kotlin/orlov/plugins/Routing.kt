package orlov.plugins

import io.ktor.server.application.*
import orlov.features.user.routes.userRoutes

fun Application.configureRouting() {
    userRoutes()
}
