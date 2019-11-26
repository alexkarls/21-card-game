package model;

public class Round {

    private final Deck DECK;
    private final Dealer RULE;
    private final Player DEALER;
    private final Player PLAYER;

    public Round(Deck deck, Dealer rule, Player dealer, Player player) {
        DECK = deck;
        RULE = rule;
        DEALER = dealer;
        PLAYER = player;
    }

    public State playerTurn() {
        PLAYER.add(DECK.draw());
        if (RULE.isLoser(PLAYER))
            return State.DEALER_WIN;
        if (RULE.isWinner(PLAYER))
            return State.PLAYER_WIN;
        return State.UNKNOWN;
    }

    public void start() {
        PLAYER.add(DECK.draw());
        PLAYER.add(DECK.draw());
    }

    public enum State {PLAYER_WIN, DEALER_WIN, UNKNOWN}
}
