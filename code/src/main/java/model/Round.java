package model;

import controller.GameController;

import java.util.LinkedList;
import java.util.List;

public class Round {

    private final Deck DECK;
    private final Rule RULE;
    private final Player DEALER;
    private final Player PLAYER;
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

    public State end() {
        return State.DEALER_WIN;
    }

    public State playerTurn() {
        PLAYER.add(DECK.draw());
        for (IRoundObserver obs : observers) {
            obs.update(PLAYER);
        }
        if (RULE.isLoser(PLAYER)) {
            return State.DEALER_WIN;
        }

        if (RULE.isWinner(PLAYER)) {
            return State.PLAYER_WIN;
        }
        return State.UNKNOWN;
    }

    public void start() {
        DECK.shuffle();
        PLAYER.add(DECK.draw());
        PLAYER.add(DECK.draw());
    }


    public State dealerTurn() {
        DEALER.add(DECK.draw());
        if (DEALER.getScore() < 17)
            return State.UNKNOWN;
        if (DEALER.getScore() > 21)
            return State.PLAYER_WIN;
        return State.DEALER_STAND;
    }

    public enum State {PLAYER_WIN, DEALER_WIN, DEALER_STAND, UNKNOWN}
}
