import model.Card;
import model.Deck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private final int DECK_SIZE = 52;
    private Deck sut;

    @BeforeEach
    void setup() {
        sut = new Deck(new Random());
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void createDeckTest() {
        assertEquals(sut.size(), DECK_SIZE);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                assertTrue(sut.draw().equals(new Card(suit, rank)));
            }
        }
        assertEquals(sut.size(), 0);
    }

    @Test
    void drawDeckTest() {
        sut = new Deck(new MockRandom());
        sut.shuffle();
        Card c = sut.draw();
        assertTrue(c.equals(new Card(Card.Suit.CLUBS, Card.Rank.TWO))); // First card (see Card class)
        while (sut.size() > 0) {
            c = sut.draw();
        }
        assertTrue(c.equals(new Card(Card.Suit.SPADES, Card.Rank.ACE))); // Last card
    }

    @Test
    void shuffleDeckTest() {
        Deck deckInOrder = new Deck(new MockRandom());
        deckInOrder.shuffle();
        assertTrue(sut.equals(deckInOrder));
        // Can be decreased or increased, can be used for statistics...
        for (int i = 0; i < 3; i++) { // Low chance to equal a new deck
            sut.shuffle();
            assertFalse(sut.equals(deckInOrder));
        }
        assertTrue(containsUniqueCards(sut));
    }

    @Test
    void equalsDeckTest() {
        assertTrue(sut.equals(new Deck(new Random())));
        sut.shuffle();
        assertFalse(sut.equals(new Deck(new Random())));
    }

    @Test
    private boolean containsUniqueCards(Deck deck) {
        List<Card> cards = new LinkedList<Card>();
        while (deck.size() > 0) {
            Card c = sut.draw();
            for (Card cardInList : cards) {
                if (cardInList.equals(c)) {
                    return false;
                }
            }
            cards.add(c);
        }
        return true;
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
