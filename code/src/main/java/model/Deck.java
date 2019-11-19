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

    public Card draw() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    // Fisher-Yates shuffle
    public void shuffle() {
        for (int i = deck.size() - 1; i >= 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    public int size() {
        return deck.size();
    }

    @Override
    public boolean equals(Object obj) {
        Deck other = (Deck) obj;
        if (other.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            Card card = deck.get(i);
            Card otherCard = other.deck.get(i);
            if (card.equals(otherCard) == false) {
                return false;
            }
        }
        return true;
    }
}
