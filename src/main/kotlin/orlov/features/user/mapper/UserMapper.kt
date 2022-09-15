package orlov.features.user.mapper

import orlov.features.user.db.entity.UserEntity
import orlov.features.user.models.UserDto

fun UserDto.mapToEntity(): UserEntity {
    return UserEntity(
        username = username,
        password = password,
        salt = salt
    )
}

fun UserEntity.mapToDto(): UserDto {
    return UserDto(
        username = username,
        password = password,
        salt = salt
    )
}