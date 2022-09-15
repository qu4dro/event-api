package orlov.features.user.db

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import orlov.features.user.db.entity.UserEntity

class UserDaoImpl(
    db: CoroutineDatabase
) : UserDao {

    private val users = db.getCollection<UserEntity>()

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return users.findOne(UserEntity::username eq username)
    }

    override suspend fun insertUser(user: UserEntity): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }
}