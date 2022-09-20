package orlov.data.dao

import orlov.data.models.UserDTO

interface UsersDao {

    suspend fun insertUser(userDTO: UserDTO)

    suspend fun fetchUser(login: String): UserDTO?

}