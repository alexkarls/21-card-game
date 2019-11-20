package model;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private final int MAX_SCORE = 21;
    private final int CARD_VALUE_OFFSET = 2;
    List<Card> hand;

    public Player() {
        hand = new LinkedList<Card>();
    }

    public void add(Card c) {
        hand.add(c);
    }

    public Iterable<Card> getHand() {
        return hand;
    }

    public int getScore() {
        int score = 0;
        for (Card c : hand) {
            score = score + c.getRank().ordinal() + CARD_VALUE_OFFSET;
        }
        if (score > MAX_SCORE) {
            for (Card c : hand) {
                if (c.getRank() == Card.Rank.ACE && score > MAX_SCORE) {
                    score = score - (c.getRank().ordinal() + (CARD_VALUE_OFFSET - 1));
                }
            }
        }
        return score;
    }

    public int size() {
        return hand.size();
    }

}
