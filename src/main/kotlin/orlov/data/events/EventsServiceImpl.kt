package orlov.data.events

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import orlov.data.mapToEventDTO

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
                event.mapToEventDTO()
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun fetchUserEvents(login: String): List<EventDTO> {
        return try {
            transaction {
                val events = Events.select {Events.creator.eq(login)}.toList()
                events.map { it.mapToEventDTO() }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

}