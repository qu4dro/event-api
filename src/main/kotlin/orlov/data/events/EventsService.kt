package orlov.data.events

interface EventsService {

    fun insertEvent(eventDTO: EventDTO)

    fun fetchEventById(id: Int): EventDTO?

    fun fetchUserEvents(login: String): List<EventDTO>

}