package controller;

public class ControllerFactory {

    public enum Controller {
        MENU,
        GAME
    }

    public IController getController(Controller controller) {
        return null;
    }
}
