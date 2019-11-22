import model.Card;
import model.Deck;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player sut;

    @BeforeEach
    void setup() {
        sut = new Player();
    }


    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void playerAddCardTest() {
        Card[] cards = new Card[]{new Card(Card.Suit.CLUBS, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.ACE),
                new Card(Card.Suit.SPADES, Card.Rank.ACE)};
        for (Card c : cards) {
            sut.add(c);
        }
        int count = 0;
        for (Card cardInHand : sut.getHand()) {
            assertTrue(cards[count].equals(cardInHand));
            count++;
        }
    }

    @Test
    void playerHandScoreTest() {
        sut.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        assertEquals(2, sut.getScore());
        sut.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        assertEquals(16, sut.getScore());
        /*
        Ace that makes score > MAX_SCORE is valued at 1
        See Player method for implementation details
         */
        sut.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        assertEquals(17, sut.getScore());
    }

    @Test
    void playerHandSizeTest() {
        Deck deck = new Deck(new MockRandom());
        sut.add(deck.draw());
        assertEquals(1, sut.size());
        sut.add(deck.draw());
        assertEquals(2, sut.size());
    }
}
