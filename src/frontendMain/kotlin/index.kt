import kotlinext.js.require
import kotlinext.js.requireAll
import react.dom.render
import kotlin.browser.document


fun main() {
    requireAll(require.context("kotlin", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        application()
    }
}