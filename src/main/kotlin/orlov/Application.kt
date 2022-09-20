package orlov

import io.ktor.server.application.*
import org.koin.core.context.startKoin
import orlov.data.db.DatabaseFactory
import orlov.di.mainModule
import orlov.plugins.*

fun main(args: Array<String>): Unit {
    DatabaseFactory.init()
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
