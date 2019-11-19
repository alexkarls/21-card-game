package model;

import java.util.LinkedList;
import java.util.List;

public class Player {

    List<Card> hand;

    public Player() {
        hand = new LinkedList<Card>();
    }

    public void add(Card c) {
        hand.add(c);
    }

    public int size() {
        return hand.size();
    }

    public Iterable<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return 10;
    }

}
