package controller;

import view.GameView;
import view.MenuView;

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
            case MENU: return new MenuController(this, new MenuView());
            case GAME: return new GameController(this, new GameView());
            default: throw new IllegalArgumentException();
        }
    }
}
