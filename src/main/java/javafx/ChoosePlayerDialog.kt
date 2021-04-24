package javafx

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.Priority
import tornadofx.*

class ChoosePlayerDialog : Dialog<ButtonType>() {
    private val whitePlayer = SimpleStringProperty()
    val whiteComputer: Boolean get() = whitePlayer.value == "Computer";

    private val blackPlayer = SimpleStringProperty()
    val blackComputer: Boolean get() = blackPlayer.value == "Computer";

    init {
        title = "Renju"
        with(dialogPane) {
            headerText = "ChoosePlayer"
            buttonTypes.add(ButtonType("Start Game", ButtonBar.ButtonData.OK_DONE))
            buttonTypes.add(ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE))
            content = hbox {
                vbox {
                    label("white")
                    togglegroup {
                        bind(whitePlayer)
                        radiobutton("Human") {
                            isSelected = true;
                        }
                        radiobutton("Computer")
                    }
                }
                spacer(Priority.SOMETIMES)
                vbox {
                    label("black")
                    togglegroup {
                        bind(blackPlayer)
                        radiobutton("Human") {
                            isSelected = true;
                        }
                        radiobutton("Computer")
                    }
                }
            }
        }
    }
}