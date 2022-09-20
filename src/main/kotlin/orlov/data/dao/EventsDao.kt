package orlov.data.dao

import orlov.data.models.EventDTO

interface EventsDao {

    suspend fun insertEvent(eventDTO: EventDTO)

    suspend fun fetchEventById(id: Int): EventDTO?

    suspend fun fetchUserEvents(login: String): List<EventDTO>

}