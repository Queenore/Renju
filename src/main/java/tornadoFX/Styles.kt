package tornadoFX

import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.Color.BLACK
import tornadofx.*

open class Styles : tornadofx.Stylesheet() {
    companion object {
        val button by cssclass()
        val status by cssclass()
        val restartButton by cssclass()
        val top by cssclass()
    }

    init {
        button {
            minHeight = 20.px
            minWidth = 20.px
            padding = box(0.px)
            focusTraversable = false
        }
        status {
            borderColor += box(BLACK, Color.WHITE, Color.WHITE, Color.WHITE)
            borderStyle += BorderStrokeStyle.SOLID
            alignment = Pos.CENTER
            padding = box(5.px)
            backgroundColor += Color.MEDIUMAQUAMARINE
            scaleX = 1.2
            scaleY = 1.2
        }
        top {
            alignment = Pos.CENTER
            padding = box(5.px, 0.px, 0.px, 0.px)
            backgroundColor += Color.WHITE
        }
        restartButton {
            padding = box(0.px, 4.px, 0.px, 4.px)
            fontSize = 15.px
        }
    }

}