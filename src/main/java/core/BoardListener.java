package core;

import org.jetbrains.annotations.NotNull;

public interface BoardListener {
    void turnMade(@NotNull Cage cage);
}
