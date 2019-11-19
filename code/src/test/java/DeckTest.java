import model.Card;
import model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    private final int DECK_SIZE = 52;
    private Deck sut;

    @BeforeEach
    void setup() {
        sut = new Deck(new Random());
    }

    @Test
    void createDeckTest() {
        assertEquals(sut.size(), DECK_SIZE);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = sut.getCard();
                assertEquals(c.getSuit(), suit);
                assertEquals(c.getRank(), rank);
            }
        }
        assertEquals(sut.size(), 0);
    }

    @Test
    void cardsFromDeckTest() {
        sut = new Deck(new MockRandom());
        sut.shuffle();
        Card c = sut.getCard();
        assertEquals(c.getSuit(), Card.Suit.CLUBS);
        assertEquals(c.getRank(), Card.Rank.TWO);

    }

    @Test
    void shuffleDeckTest() {
        Deck inOrderDeck = new Deck(new Random());
        sut.shuffle();
        assertFalse(sut.equals(inOrderDeck));
    }

    class MockRandom extends Random {

        private int value = DECK_SIZE;

        MockRandom() {
            super();
        }

        @Override
        public int nextInt(int v) {
            value--; // Increment down with each card (swap card with same card)
            return value;
        }
    }
}
