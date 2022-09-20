package orlov.data.dao

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import orlov.data.db.DatabaseFactory.dbQuery
import orlov.data.db.Users
import orlov.data.db.mapToUserDTO
import orlov.data.models.UserDTO

class UsersDaoImpl : UsersDao {

    override suspend fun insertUser(userDTO: UserDTO) {
        dbQuery {
            Users.insert {
                it[password] = userDTO.password
                it[login] = userDTO.login
                it[username] = userDTO.username
                it[salt] = userDTO.salt
            }
        }
    }

    override suspend fun fetchUser(login: String): UserDTO? {
        return try {
            dbQuery {
                val user = Users.select { Users.login.eq(login) }.single()
                user.mapToUserDTO()
            }
        } catch (e: Exception) {
            null
        }
    }

}