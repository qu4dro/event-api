package orlov.features.user.models

import org.bson.codecs.pojo.annotations.BsonId

data class UserDto(
    val username: String,
    val password: String,
    val salt: String,
)