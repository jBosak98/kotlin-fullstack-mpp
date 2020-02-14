import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import model.User

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {

    routing {
        get("/") {
            val user = User(1, "KtorBackendUser")
            call.respondText("Hello, $user!", ContentType.Text.Plain, HttpStatusCode.OK)
        }
    }
}
