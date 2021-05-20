package core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Board {

    private final int width;

    private final int height;

    public static int TO_WIN_LENGTH = 5;

    private final Set<Cage> set = new HashSet<>();

    private Cage turnCage;

    BoardListener listener = null;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Cage> getSet() {
        return set;
    }

    public void createNewListener(BoardListener listener) {
        this.listener = listener;
    }

    public void makeTurn(Cage cage) {
        turnCage = cage;
        listener.turn(cage);
    }

    public void updateBoard(@NotNull Boolean turnNumber) {
        turnCage.setColor(turnNumber);
        set.add(turnCage);
    }

    @Nullable
    private Cage getCageFromSet(Cage currentCage) {
        for (Cage item : set)
            if (item.equals(currentCage)) return item;
        return null;
    }

    public WinningCombo winningCombo(Cage cage) {
        if (!set.contains(cage)) return null;
        int line = 0;
        Cage startCage = null;
        Cage endCage = null;
        Cage plusMinusCage = new Cage(1, 1);
        for (int direction = 0; direction < 4; direction++) {
            for (int j = 0; j < 2; j++) {
                Cage currentCage = cage;
                Boolean stepColor;
                do {
                    line++;
                    if (j == 0) {
                        startCage = currentCage;
                        currentCage = currentCage.plus(plusMinusCage);
                    } else {
                        endCage = currentCage;
                        currentCage = currentCage.minus(plusMinusCage);
                    }
                    if (getCageFromSet(currentCage) == null) break;
                    stepColor = getCageFromSet(currentCage).getColor(); // not null
                } while (stepColor == getCageFromSet(cage).getColor()); // not null
            }
            if (line >= TO_WIN_LENGTH + 1) {
                return new WinningCombo(startCage, endCage, plusMinusCage);
            } else {
                line = 0;
                if (direction == 0) plusMinusCage = new Cage(1, 0);
                else if (direction == 1) plusMinusCage = new Cage(0, 1);
                else plusMinusCage = new Cage(-1, 1);
            }
        }
        return null;
    }

    public void clearBoard() {
        set.clear();
    }

}
