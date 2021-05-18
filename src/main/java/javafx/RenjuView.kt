package javafx

import controller.BoardBasedCageListener
import core.*
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import tornadofx.*

class RenjuView : View(), BoardListener {

    override val root = BorderPane()

    private val rows = 16

    private val columns = 16

    private val board = Board(rows, columns)

    private val buttons = mutableMapOf<Cage, Button>()

    private var inProcess = true

    private var turnNumber = false

    private lateinit var status: Label

    init {
        title = "Renju"
        val listener = BoardBasedCageListener(board)
        board.createNewListener(this)
        with(root) {
            top {
                hbox {
                    val restartButton = button {
                        text = "restart"
                        tooltip("click to restart the game")
                        addClass(Styles.restartButton)
                    }
                    addClass(Styles.top)
                    restartButton.action {
                        restartGame()
                    }
                }
            }
            center {
                gridpane {
                    for (row in 0..rows) {
                        row {
                            for (column in 0..columns) {
                                val cage = Cage(column, (rows - row))
                                val button = when {
                                    (row == 0 && column == 0) -> button(graphic = ImageView("/cageTopLeft.png"))
                                    (row == 0 && column == 16) -> button(graphic = ImageView("/cageTopRight.png"))
                                    (row == 16 && column == 16) -> button(graphic = ImageView("/cageBotRight.png"))
                                    (row == 16 && column == 0) -> button(graphic = ImageView("/cageBotLeft.png"))
                                    (column == 16) -> button(graphic = ImageView("/verticalRight.png"))
                                    (column == 0) -> button(graphic = ImageView("/verticalLeft.png"))
                                    (row == 16) -> button(graphic = ImageView("/horizontalBot.png"))
                                    (row == 0) -> button(graphic = ImageView("/horizontalTop.png"))
                                    else -> button(graphic = ImageView("/cage.png"))
                                }
                                addClass(Styles.button)
                                button.action {
                                    if (inProcess) {
                                        listener.cageClicked(cage)
                                    }
                                }
                                if (row != 0 && row != 16 && column != 0 && column != 16)
                                    buttons[cage] = button
                            }
                        }
                    }
                }
            }
            bottom {
                hbox {
                    addClass(Styles.status)
                    status = label("white's turn")
                }
            }
        }
    }

    override fun turn(cage: Cage) = updateGame(cage)

    private fun updateGame(cage: Cage) {
        if (cage.x in 1..15 && cage.y in 1..15 && !board.set.contains(cage)) {
            turnNumber = turnNumber == false
            board.updateBoard(turnNumber)
            buttons[cage]?.apply {
                if (turnNumber) button(graphic = ImageView("/cageWhite.png"))
                else button(graphic = ImageView("/cageBlack.png"))
            }
            if (board.winningCombo(cage) != null) {
                inProcess = false
                val combo = board.winningCombo(cage)
                var startCage = combo.startCage
                while (startCage != combo.endCage.minus(combo.directionCage)) {
                    buttons[startCage]?.apply {
                        button(graphic = ImageView("/cageRed.png"))
                        startCage = startCage.minus(combo.directionCage)
                    }
                }
            }
        }
        status.text = when {
            board.set.size == board.height * board.width -> "draw"
            !inProcess && turnNumber -> "the end, white won"
            !inProcess -> "the end, black won"
            turnNumber -> "black's turn"
            else -> "white's turn"
        }
        if (status.text == "draw") inProcess = false
    }

    private fun restartGame() {
        inProcess = true
        turnNumber = false
        board.clearBoard()
        for ((_, button) in buttons) {
            button.apply {
                button(graphic = ImageView("/cage.png"))
            }
        }
        status.text = "white's turn"
    }
}
