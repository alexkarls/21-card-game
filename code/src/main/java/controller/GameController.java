package controller;

import model.IRoundObserver;
import model.Player;
import model.Round;
import view.GameView;

public class GameController implements IController, IRoundObserver {

    private ControllerFactory controllers;
    private GameView view;
    private Round round;
    private boolean isDealerTurn = false;

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
        switch (result) {
            case EXIT:
                return false;
            case DEALER_WIN:
                System.out.println("Dealer wins...");
                return true;
            case PLAYER_WIN:
                System.out.println("Player wins...");
                return true;
        }
        return false;
    }

    public Round.State play(Round round) {
        return round.playerTurn();
    }

    @Override
    public void update(Player player) {
        view.displayPlayer(player, isDealerTurn);
    }
}
