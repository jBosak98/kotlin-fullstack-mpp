import model.User
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.p

class Application : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        val user = User(2, "ReactFrontendUser")
        div {
            p {
                +"Hello, $user!"
            }
        }
    }
}

fun RBuilder.application() = child(Application::class) {}