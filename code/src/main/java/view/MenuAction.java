package view;

public enum MenuAction {

    PLAY("Play 21"),
    EXIT("Exit");

    private final String TEXT;

    MenuAction(String TEXT) {
        this.TEXT = TEXT;
    }

    public String toString() {
        return TEXT;
    }
}
