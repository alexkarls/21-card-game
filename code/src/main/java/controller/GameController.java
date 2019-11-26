package controller;

public class GameController implements IController {

    private ControllerFactory controllers;

    public GameController(ControllerFactory controllers, Object o1) {
        this.controllers = controllers;
    }

    @Override
    public boolean run() {
        return true;
    }
}
