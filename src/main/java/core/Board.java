package core;

import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int width;

    private final int height;

    private final Map<Cage, CageColor> map = new HashMap<>();

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

    public void createNewListener(BoardListener listener) {
        this.listener = listener;
    }

    public void makeTurn(Cage cage) {
        listener.turnMade(cage);
    }

    public void updateColorMap(Map<Cage, Button> mapWithButtons, Cage currentCage, int turnNumber) {
        if (turnNumber == 1)
            for (Cage key : mapWithButtons.keySet())
                if (key == currentCage) map.put(key, CageColor.WHITE);
                else map.put(key, null);
        else if (turnNumber % 2 == 1) map.put(currentCage, CageColor.WHITE);
        else map.put(currentCage, CageColor.BLACK);
    }

    public boolean winningCombo(Cage cage) {
        int line = 1;
        Cage plusMinusCage = new Cage(1, 1);
        for (int direction = 0; direction < 4; direction++) {
            for (int j = 0; j < 2; j++) {
                Cage currentCage;
                if (j == 0) currentCage = cage.plus(plusMinusCage);
                else currentCage = cage.minus(plusMinusCage);
                CageColor stepColor = map.get(currentCage);
                while (stepColor == map.get(cage)) {
                    line++;
                    if (j == 0) currentCage = currentCage.plus(plusMinusCage);
                    else currentCage = currentCage.minus(plusMinusCage);
                    stepColor = map.get(currentCage);
                }
            }
            if (line == 5) return true;
            else {
                line = 1;
                if (direction == 0) plusMinusCage = new Cage(1, 0);
                else if (direction == 1) plusMinusCage = new Cage(0, 1);
                else plusMinusCage = new Cage(-1, 1);
            }

        }
        return false;
    }

}
