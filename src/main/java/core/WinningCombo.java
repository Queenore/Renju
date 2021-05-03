package core;

public class WinningCombo {
    private final Cage startCage;
    private final Cage endCage;
    private final Cage directionCage;

    public WinningCombo(Cage startCage, Cage endCage, Cage directionCage) {
        this.startCage =  startCage;
        this.endCage = endCage;
        this.directionCage = directionCage;
    }

    public Cage getStartCage() {
        return startCage;
    }

    public Cage getEndCage() {
        return endCage;
    }

    public Cage getDirectionCage() {
        return directionCage;
    }
}
