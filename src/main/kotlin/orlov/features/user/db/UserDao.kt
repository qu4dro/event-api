package orlov.features.user.db

import orlov.features.user.db.entity.UserEntity

interface UserDao {
    suspend fun getUserByUsername(username: String): UserEntity?
    suspend fun insertUser(user: UserEntity): Boolean
}