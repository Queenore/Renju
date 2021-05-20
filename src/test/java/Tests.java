import static org.junit.Assert.*;

import core.Board;
import core.Cage;
import org.junit.Test;

public class Tests {

    @Test
    public void winningComboDiagonalTest() {
        Board board = new Board(15, 15);
        for (int i = 1; i < 6; i++) {
            try {
                board.makeTurn(new Cage(i, i));
            } catch (NullPointerException ignored) {
            }
            board.updateBoard(true);
        }
        if (board.winningCombo(new Cage(3, 3)) != null) {
            assertNotNull(board.winningCombo(new Cage(5, 5)));
        }
        else fail();
    }

    @Test
    public void winningComboHorizontalTest() {
        Board board = new Board(15, 15);
        for (int i = 5; i < 10; i++) {
            try {
                board.makeTurn(new Cage(i, 7));
            } catch (NullPointerException ignored) {
            }
            board.updateBoard(true);
        }
        if (board.winningCombo(new Cage(7, 7)) != null) {
            assertNotNull(board.winningCombo(new Cage(7, 7)));
        }
        else fail();
    }

    @Test
    public void winningComboVerticalTest() {
        Board board = new Board(15, 15);
        for (int i = 3; i < 8; i++) {
            try {
                board.makeTurn(new Cage(2, i));
            } catch (NullPointerException ignored) {
            }
            board.updateBoard(true);
        }
        if (board.winningCombo(new Cage(2, 5)) != null) {
            assertNotNull(board.winningCombo(new Cage(2, 5)));
        }
        else fail();
    }

}
