package model;

public class Dealer {

    private final int MAX_SCORE = 21;

    public boolean isLoser(Player player) {
        return player.getScore() > MAX_SCORE;
    }

    public boolean isWinner(Player player) {
        return true;
    }

    public Player returnWinner(Player dealer, Player player) {
        return dealer;
    }
}
