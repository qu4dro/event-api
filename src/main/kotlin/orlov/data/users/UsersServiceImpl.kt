package orlov.data.users

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import orlov.data.mapToUserDTO

class UsersServiceImpl : UsersService {

    override fun insertUser(userDTO: UserDTO) {
        transaction {
           Users.insert {
                it[password] = userDTO.password
                it[login] = userDTO.login
                it[username] = userDTO.username
                it[salt] = userDTO.salt
            }
        }
    }

    override fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val user = Users.select { Users.login.eq(login) }.single()
                user.mapToUserDTO()
            }
        } catch (e: Exception) {
            null
        }
    }

}