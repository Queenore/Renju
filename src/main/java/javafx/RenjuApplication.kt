package javafx

import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.App
import tornadofx.setStageIcon

class RenjuApplication : App(RenjuView::class, Styles::class) {

    override fun start(stage: Stage) {
        setStageIcon(Image("/icon.png"))
        super.start(stage)
    }
}

fun main() {
    Application.launch(RenjuApplication::class.java)
}