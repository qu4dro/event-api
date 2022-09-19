package orlov.data.events

interface EventsService {

    fun insertEvent(eventDTO: EventDTO)

    fun fetchEventById(id: Int): EventDTO?

}