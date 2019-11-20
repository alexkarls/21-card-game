package model;

public class Dealer {

    private final int MAX_HAND = 5;
    private final int MAX_SCORE = 21;

    public boolean isLoser(Player player) {
        return player.getScore() > MAX_SCORE;
    }

    public boolean isWinner(Player player) {
        int score = player.getScore();
        if (score == MAX_SCORE) {
            return true;
        }
        return score < MAX_SCORE && player.size() == MAX_HAND;
    }

    public Player returnWinner(Player dealer, Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > MAX_SCORE) {
            return dealer;
        }
        if (dealerScore > MAX_SCORE) {
            return player;
        }
        if (playerScore == dealerScore) {
            return dealer;
        }
        if (playerScore < dealerScore) {
            return dealer;
        }
        return player;
    }
}

