package orlov.features.auth.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticate() {
    authenticate {
        get("/api/v1/authentication") {
            call.respond(HttpStatusCode.OK)
        }
    }
}