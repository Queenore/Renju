package javafx

import controller.BoardBasedCageListener
import core.*
import javafx.scene.control.Button
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

    private var turnNumber = 0

    private var map = mutableMapOf<Cage, CageColor>()

    init {
        title = "Renju"
        val listener = BoardBasedCageListener(board)
        board.createNewListener(this)
        with(root) {
            top {
                hbox {
                    label("//////////")
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
                    label("/////////////")
                }
            }
        }
    }

    override fun turnMade(cage: Cage) = updateBoard(cage)

    private fun initColorMap() {
        map = board.map
    }

    private fun updateBoard(cage: Cage) {
        initColorMap()
        if (cage.x in 1..15 && cage.y in 1..15 && map[cage] == null) {
            turnNumber++
            board.updateColorMap(buttons, cage, turnNumber)
            if (turnNumber % 2 == 1)
                buttons[cage]?.apply { graphic = ImageView("/cageWhite.png") }
            else
                buttons[cage]?.apply { graphic = ImageView("/cageBlack.png") }
            if (board.winningCombo(cage) != null) {
                val combo = board.winningCombo(cage)
                var startCage = combo.startCage
                while (startCage != combo.endCage.minus(combo.directionCage)) {
                    buttons[startCage]?.apply { graphic = ImageView("/cageRed.png") }
                    startCage = startCage.minus(combo.directionCage)
                }
            }
        }
    }

}
