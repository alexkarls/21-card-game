package controller;

import model.IRoundObserver;
import model.Player;
import model.Round;
import view.GameAction;
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
        round.start();

        view.displayGame();
        GameAction action = view.getInputAction();
        Round.State state = Round.State.UNKNOWN;

        while (action == GameAction.HIT && state == Round.State.UNKNOWN) {
            state = round.playerTurn();
        }

        if (state != Round.State.UNKNOWN) {
            return state;
        }

        while (state == Round.State.UNKNOWN) {
            state = round.dealerTurn();
        }

        if (state != Round.State.DEALER_STAND) {
            return state;
        }


        return round.end();
    }

    @Override
    public void update(Player player) {
        view.displayPlayer(player, isDealerTurn);
    }
}
