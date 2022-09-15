package orlov.features.user.db.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserEntity(
    val username: String,
    val password: String,
    val salt: String,
    @BsonId
    val id: ObjectId = ObjectId()
)
