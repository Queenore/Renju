package controller;

import core.Cage;
import org.jetbrains.annotations.NotNull;

interface CageListener {
    void cageClicked(@NotNull Cage cage);
}
