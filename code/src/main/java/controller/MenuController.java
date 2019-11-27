package controller;

import model.Deck;
import model.Player;
import model.Round;
import model.Rule;
import view.MenuAction;
import view.MenuView;

import java.util.Random;

public class MenuController implements IController {

    private ControllerFactory controllers;
    private MenuView view;

    public MenuController(ControllerFactory controllers, MenuView view) {
        this.controllers = controllers;
        this.view = view;
    }

    @Override
    public boolean run() {
        view.displayMenu();
        MenuAction action = view.getInputAction();

        if (action == null) {
            return true;
        }

        switch (action) {
            case PLAY:
                GameController gameController = (GameController) controllers.getController(ControllerFactory.Controller.GAME);
                gameController.set(new Round(new Deck(new Random()), new Rule(), new Player(), new Player()));
                gameController.run();
                break;
            case EXIT:
                return false;
        }

        return false;
    }
}
