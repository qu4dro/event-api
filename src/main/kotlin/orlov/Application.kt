package orlov

import io.ktor.server.application.*
import org.koin.core.context.startKoin
import orlov.di.mainModule
import orlov.features.user.di.authenticationModule
import orlov.plugins.*

fun main(args: Array<String>): Unit {
    startKoin { modules(mainModule, authenticationModule) }
    io.ktor.server.netty.EngineMain.main(args)
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
//
//    val tokenConfig = TokenConfig(
//        issuer = environment.config.property("jwt.issuer").getString(),
//        audience = environment.config.property("jwt.audience").getString(),
//        expiresIn = 365L * 1000L * 60L * 24L,
//        secret = System.getenv("JWT_SECRET")
//    )
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
