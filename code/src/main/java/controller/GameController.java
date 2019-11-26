package controller;

import model.IRoundObserver;
import model.Player;

public class GameController implements IController, IRoundObserver {

    private ControllerFactory controllers;

    public GameController(ControllerFactory controllers, Object o1) {
        this.controllers = controllers;
    }

    @Override
    public boolean run() {
        return true;
    }

    @Override
    public void update(Player player) {

    }
}
