package orlov.data.events

data class EventDTO(
    val name: String,
    val description: String,
    val dateTime: Long,
    val creator: String,
    val lng: String,
    val lat: String
)
