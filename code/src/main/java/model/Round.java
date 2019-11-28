package model;

import controller.GameController;

import java.util.LinkedList;
import java.util.List;

public class Round {

    private final Deck DECK;
    private final Rule RULE;
    private final Player DEALER;
    private final Player PLAYER;
    private final int DEALER_STAND_SCORE = 17;
    private final int MAX_SCORE = 21;
    private List<IRoundObserver> observers;

    public Round(Deck deck, Rule rule, Player dealer, Player player) {
        DECK = deck;
        RULE = rule;
        DEALER = dealer;
        PLAYER = player;
        observers = new LinkedList<>();
    }

    public void subscribe(GameController observer) {
        observers.add(observer);
    }

    private void updateObservers(Player player) {
        for (IRoundObserver obs : observers) {
            obs.update(player);
        }
    }

    public Player getDealer() {
        return DEALER;
    }

    public Player getPlayer() {
        return PLAYER;
    }

    public State start() {
        DECK.shuffle();
        dealTo(PLAYER);
        dealTo(PLAYER);
        return playerState();
    }

    public State end() {
        if (RULE.returnWinner(DEALER, PLAYER) == DEALER) {
            return State.DEALER_WIN;
        }
        return State.PLAYER_WIN;
    }

    public State dealerTurn() {
        dealTo(DEALER);
        return dealerState();
    }

    public State playerTurn() {
        if (playerState() != State.UNKNOWN) {
            return playerState();
        }
        dealTo(PLAYER);
        return playerState();
    }

    private State dealerState() {
        int dealerScore = DEALER.getScore();
        if (dealerScore < DEALER_STAND_SCORE) {
            return State.UNKNOWN;
        }
        if (dealerScore > MAX_SCORE) {
            return State.PLAYER_WIN;
        }
        return State.DEALER_STAND;
    }

    private State playerState() {
        if (RULE.isLoser(PLAYER)) {
            return State.DEALER_WIN;
        }
        if (RULE.isWinner(PLAYER)) {
            return State.PLAYER_WIN;
        }
        return State.UNKNOWN;
    }

    private void dealTo(Player player) {
        player.add(DECK.draw());
        updateObservers(player);
    }

    public enum State {
        PLAYER_WIN, DEALER_WIN, DEALER_STAND, UNKNOWN, EXIT
    }
}
