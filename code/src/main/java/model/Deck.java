package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    List<Card> deck;
    Random random;

    public Deck(Random random) {
        this.random  = random;
        deck = new LinkedList<Card>();
        build();
    }

    private void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
    }

    public Card getCard() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    public int size() {
        return deck.size();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
