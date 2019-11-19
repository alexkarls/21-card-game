package model;

import java.util.LinkedList;
import java.util.List;

public class Deck {

    List<Card> deck;

    public Deck() {
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

    public Card getCard() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    public int size() {
        return deck.size();
    }
}
