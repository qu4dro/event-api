package orlov.data.dao

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import orlov.data.db.DatabaseFactory.dbQuery
import orlov.data.db.Events
import orlov.data.db.mapToEventDTO
import orlov.data.models.EventDTO

class EventsDaoImpl : EventsDao {

    override suspend fun insertEvent(eventDTO: EventDTO) {
        dbQuery {
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

    override suspend fun fetchEventById(id: Int): EventDTO? {
        return try {
            dbQuery {
                val event = Events.select { Events.id.eq(id) }.single()
                event.mapToEventDTO()
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchUserEvents(login: String): List<EventDTO> {
        return try {
            dbQuery {
                val events = Events.select {Events.creator.eq(login)}.toList()
                events.map { it.mapToEventDTO() }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

}