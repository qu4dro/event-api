package orlov.data.users

data class UserDTO(
    val password: String,
    val login: String,
    val username: String,
    val salt: String
)