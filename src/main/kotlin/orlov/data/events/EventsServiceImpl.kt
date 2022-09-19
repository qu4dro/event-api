package orlov.data.events

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class EventsServiceImpl : EventsService {

    override fun insertEvent(eventDTO: EventDTO) {
        transaction {
            Events.insert {
                it[name] = eventDTO.name
                it[description] = eventDTO.description
                it[dateTime] = eventDTO.dateTime
                it[creator] = eventDTO.creator
                it[lng] = eventDTO.lng
                it[lat] = eventDTO.lat
            }
        }
    }

    override fun fetchEventById(id: Int): EventDTO? {
        return try {
            transaction {
                val event = Events.select { Events.id.eq(id) }.single()
                EventDTO(
                    name = event[Events.name],
                    description = event[Events.description],
                    dateTime = event[Events.dateTime],
                    creator = event[Events.creator],
                    lng = event[Events.lng],
                    lat = event[Events.lat]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

}