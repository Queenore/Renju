package core;

public class Cage {

    public final int x;
    public final int y;

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
}
