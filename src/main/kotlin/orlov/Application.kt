package orlov

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin
import org.slf4j.LoggerFactory
import orlov.data.events.Events
import orlov.di.mainModule
import orlov.plugins.*
import orlov.data.users.Users

fun main(args: Array<String>): Unit {

    val dbname = System.getenv("DB_NAME")
    val user = System.getenv("POSTGRES_USER")
    val password = System.getenv("POSTGRES_PW")

    Database.connect("jdbc:postgresql://localhost:5432/$dbname", driver = "org.postgresql.Driver",
        user = user, password = password)

    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")

    transaction {
        SchemaUtils.create(Users, Events)
        //Do stuff
    }

    startKoin { modules(mainModule) }
    io.ktor.server.netty.EngineMain.main(args)
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
