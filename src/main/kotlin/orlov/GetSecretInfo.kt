package orlov

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//fun Route.getSecretInfo() {
//    authenticate {
//        get("/api/v1/user/secret") {
//            val principal = call.principal<JWTPrincipal>()
//            val login = principal?.getClaim("login", String::class)
//            call.respond(HttpStatusCode.OK, "Your login $login")
//        }
//    }
//}