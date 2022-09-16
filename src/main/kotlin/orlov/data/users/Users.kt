package orlov.data.users

import org.jetbrains.exposed.sql.Table


object Users : Table("users") {

    private val id = Users.integer("id").autoIncrement()
    val password = Users.varchar("password", 128)
    val login = Users.varchar("login", 25).uniqueIndex()
    val username = Users.varchar("username", 25)
    val salt = Users.varchar("salt", 128)

    override val primaryKey = PrimaryKey(id)

}