package orlov.features.user.repository

import orlov.features.user.db.entity.UserEntity
import orlov.features.user.models.UserDto

interface UserRepository {

    suspend fun getUserByUserName(username: String): UserEntity?

    suspend fun insertUser(user: UserDto): Boolean

}