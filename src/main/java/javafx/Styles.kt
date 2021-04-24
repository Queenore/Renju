package javafx

import tornadofx.*

open class Styles : tornadofx.Stylesheet() {
    companion object {
        val button by cssclass()
    }

    init {
        button {
            minHeight = 20.px
            minWidth = 20.px
            padding = box(0.px)
            focusTraversable = false
        }
    }

}