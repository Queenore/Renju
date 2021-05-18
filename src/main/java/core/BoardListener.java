package core;

import org.jetbrains.annotations.NotNull;

public interface BoardListener {
    void turn(@NotNull Cage cage);
}
