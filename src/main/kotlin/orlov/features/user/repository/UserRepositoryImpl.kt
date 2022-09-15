package orlov.features.user.repository

import orlov.features.user.db.UserDao
import orlov.features.user.db.entity.UserEntity
import orlov.features.user.mapper.mapToDto
import orlov.features.user.mapper.mapToEntity
import orlov.features.user.models.UserDto

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun getUserByUserName(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    override suspend fun insertUser(user: UserDto): Boolean {
        return userDao.insertUser(user.mapToEntity())
    }

}