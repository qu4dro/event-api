package orlov.features.events.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent
import orlov.data.dao.EventsDao
import orlov.features.events.FetchEventRequest
import orlov.features.events.FetchEventResponse

fun Route.fetchEvent() {

    val eventsDao: EventsDao by KoinJavaComponent.inject(EventsDao::class.java)

    authenticate {
        get("/api/v1/events/event") {
            val principal = call.principal<JWTPrincipal>()
            val userLogin = principal?.getClaim("login", String::class)
            if (userLogin.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val request = call.receiveNullable<FetchEventRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val event = eventsDao.fetchEventById(request.eventId)

            if (event == null) {
                call.respond(HttpStatusCode.Conflict, "Event not found")
                return@get
            }

            call.respond(
                HttpStatusCode.OK, message = FetchEventResponse(
                    name = event.name,
                    description = event.description,
                    dateTime = event.dateTime,
                    creator = event.creator,
                    lng = event.lng,
                    lat = event.lat
                )
            )
        }
    }

}