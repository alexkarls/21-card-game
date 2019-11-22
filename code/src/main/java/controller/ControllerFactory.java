package controller;

public class ControllerFactory {

    public enum Controller {
        MENU,
        GAME
    }

    public IController getController(Controller controller) {
        if (controller == null) {
            throw new IllegalArgumentException();
        }
        switch (controller) {
            case MENU: return new MenuController(null, null);
            case GAME: return new GameController(null, null);
            default: throw new IllegalArgumentException();
        }
    }
}
