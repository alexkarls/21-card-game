package controller;

import model.IRoundObserver;
import model.Player;
import model.Round;
import view.GameView;

public class GameController implements IController, IRoundObserver {

    private ControllerFactory controllers;
    private GameView view;
    private Round round;

    public GameController(ControllerFactory controllers, GameView view) {
        this.controllers = controllers;
        this.view = view;
    }

    public void set(Round round) {
        this.round = round;
    }

    @Override
    public boolean run() {
        Round.State result = play(round);
        System.out.println(result);
        if (result.equals(Round.State.EXIT)) {
            return false;
        }
        return true;
    }

    public Round.State play(Round round) {
        return round.playerTurn();
    }

    @Override
    public void update(Player player) {

    }
}
