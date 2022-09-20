package orlov.data.db

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DatabaseFactory {

    private val dbname = System.getenv("DB_NAME")
    private val user = System.getenv("POSTGRES_USER")
    private val password = System.getenv("POSTGRES_PW")

    fun init() {
        Database.connect(
            "jdbc:postgresql://localhost:5432/$dbname",
            driver = "org.postgresql.Driver",
            user = user,
            password = password
        )

        LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")

        transaction {
            SchemaUtils.create(Users, Events)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}