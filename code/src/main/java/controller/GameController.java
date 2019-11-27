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

    public GameController(ControllerFactory controllers, GameView view) {
        this.controllers = controllers;
        this.view = view;
    }

    public void set(Round round) {
        this.round = round;
        this.round.subscribe(this);
    }

    @Override
    public boolean run() {
        Round.State result = play(round);
        switch (result) {
            case EXIT:
                return false;
            case DEALER_WIN:
                view.displayPlayer(round.getDealer(), true);
                return true;
            case PLAYER_WIN:
                view.displayPlayer(round.getPlayer(), false);
                return true;
        }
        return false;
    }

    private GameAction getAction() {
        view.displayGame();
        return view.getInputAction();
    }

    public Round.State play(Round round) {
        round.start();
        GameAction action = getAction();
        Round.State state = Round.State.UNKNOWN;

        while (action == GameAction.HIT) {
            state = round.playerTurn();
            if (state != Round.State.UNKNOWN) {
                return state;
            }
            action = getAction();
        }

        if (action == GameAction.EXIT) {
            return Round.State.EXIT;
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
        if (player.equals(round.getDealer())) {
            view.displayPlayer(player, true);
        } else {
            view.displayPlayer(player, false);
        }
    }
}
