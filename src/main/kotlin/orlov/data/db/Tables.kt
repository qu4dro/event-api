package orlov.data.db

import org.jetbrains.exposed.sql.Table

object Events : Table("events") {

    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val description = varchar("description", 255)
    val dateTime = long("date_time")
    val creator = reference("creator", Users.login)
    val lng = varchar("lng", 16)
    val lat = varchar("lat", 16)

    override val primaryKey = PrimaryKey(id)
}

object Users : Table("users") {

    val id = Users.integer("id").autoIncrement()
    val password = Users.varchar("password", 128)
    val login = Users.varchar("login", 25).uniqueIndex()
    val username = Users.varchar("username", 25)
    val salt = Users.varchar("salt", 128)

    override val primaryKey = PrimaryKey(id)

}