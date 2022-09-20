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
import orlov.data.models.EventDTO
import orlov.data.dao.EventsDao
import orlov.features.events.CreateEventRequest
import orlov.utils.validate

fun Route.createEvent() {

    val eventsDao: EventsDao by KoinJavaComponent.inject(EventsDao::class.java)

    authenticate {
        post("/api/v1/events/event") {
            val principal = call.principal<JWTPrincipal>()
            val userLogin = principal?.getClaim("login", String::class)
            if (userLogin.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val request = call.receiveNullable<CreateEventRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val event = EventDTO(
                name = request.name,
                description = request.description,
                dateTime = request.dateTime,
                creator = userLogin!!,
                lng = request.lng,
                lat = request.lat
            )

            if (!event.validate()) {
                call.respond(HttpStatusCode.BadRequest, "Validation failed, please check the entered information")
                return@post
            }

            try {
                eventsDao.insertEvent(event)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Can not insert event")
                return@post
            }

            call.respond(HttpStatusCode.OK)
        }

    }

}

