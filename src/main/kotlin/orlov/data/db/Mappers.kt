package orlov.data.db

import org.jetbrains.exposed.sql.ResultRow
import orlov.data.models.EventDTO
import orlov.data.models.UserDTO

fun ResultRow.mapToEventDTO() = EventDTO(
    name = this[Events.name],
    description = this[Events.description],
    dateTime = this[Events.dateTime],
    creator = this[Events.creator],
    lng = this[Events.lng],
    lat = this[Events.lat]
)

fun ResultRow.mapToUserDTO() = UserDTO(
    password = this[Users.password],
    login = this[Users.login],
    username = this[Users.username],
    salt = this[Users.salt]
)