package orlov.data.users

interface UsersService {

    fun insertUser(userDTO: UserDTO)

    fun fetchUser(login: String): UserDTO?

}