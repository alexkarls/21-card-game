package controller;

import view.MenuAction;
import view.MenuView;

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
                controllers.getController(ControllerFactory.Controller.GAME).run();
                break;
            case EXIT:
                return false;
        }

        return false;
    }
}
