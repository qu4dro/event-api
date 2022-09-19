package orlov.features.events.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.eventRoutes() {
    routing {
        createEvent()
        fetchEvent()
    }
}