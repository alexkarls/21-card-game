package view;

public enum GameAction {

    HIT("HIT"),
    STAND("STAND"),
    EXIT("EXIT");

    private final String TEXT;

    GameAction(String TEXT) {
        this.TEXT = TEXT;
    }

    public String toString() {
        return TEXT;
    }
}
