package orlov.security.token

interface TokenService {
    fun generate(
        login: String
    ): String
}