package javafx

import javafx.application.Application
import javafx.stage.Stage
import tornadofx.App

class RenjuApplication : App(RenjuView::class, Styles::class) {

    override fun start(stage: Stage) {
        val dialog = ChoosePlayerDialog()
        val result = dialog.showAndWait()
        super.start(stage)
    }
}

fun main() {
    Application.launch(RenjuApplication::class.java)
}