package orlov.features.events.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.java.KoinJavaComponent
import orlov.data.dao.EventsDao
import orlov.data.models.EventDTO
import orlov.features.events.FetchNearbyEventsRequest
import orlov.features.events.FetchUserEventsResponse

fun Route.fetchNearbyEvents() {

    val eventsDao: EventsDao by KoinJavaComponent.inject(EventsDao::class.java)

    authenticate {
        post("/api/v1/events/nearby") {
            val principal = call.principal<JWTPrincipal>()
            val userLogin = principal?.getClaim("login", String::class)
            if (userLogin.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val request = call.receiveNullable<FetchNearbyEventsRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val events: List<EventDTO>

            try {
                events = eventsDao.fetchNearbyEvents()
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Can not find events")
                return@post
            }

            if (events.isNullOrEmpty()) {
                call.respond(HttpStatusCode.Conflict, "Can not find events")
                return@post
            }

            call.respond(HttpStatusCode.OK, message = FetchUserEventsResponse(events))
        }
    }

}