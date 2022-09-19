package orlov.data

import org.jetbrains.exposed.sql.ResultRow
import orlov.data.events.EventDTO
import orlov.data.events.Events
import orlov.data.users.UserDTO
import orlov.data.users.Users

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