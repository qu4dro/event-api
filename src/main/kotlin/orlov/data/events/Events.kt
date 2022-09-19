package orlov.data.events

import org.jetbrains.exposed.sql.Table

object Events : Table("events") {

    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val description = varchar("description", 255)
    val dateTime = long("date_time")
    val creator = varchar("creator", 25)
    val lng = varchar("lng", 16)
    val lat = varchar("lat", 16)

    override val primaryKey = PrimaryKey(id)
}