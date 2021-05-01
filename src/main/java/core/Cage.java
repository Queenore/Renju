package core;

import java.util.Objects;

public class Cage {

    private final int x;
    private final int y;

    public Cage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY () {
        return this.y;
    }

    public Cage plus(Cage cage) {
        return new Cage(x + cage.x, y + cage.y);
    }

    public Cage minus(Cage cage) {
        return new Cage(x - cage.x, y - cage.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cage cage = (Cage) o;
        return x == cage.x &&
                y == cage.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

}
