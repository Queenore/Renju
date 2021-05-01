package controller;

import core.Board;
import core.Cage;
import org.jetbrains.annotations.NotNull;

public class BoardBasedCageListener implements CageListener {

    private final Board board;

    public BoardBasedCageListener(Board board) {
        this.board = board;
    }

    @Override
    public void cageClicked(@NotNull Cage cage) {
        board.makeTurn(cage);
    }
}