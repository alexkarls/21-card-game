import model.Card;
import model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player sut;

    @BeforeEach
    void setup() {
        sut = new Player();
    }

    @Test
    void addCardPlayerTest() {
        Card c = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        sut.add(c);
        for (Card cardInHand : sut.getHand()) {
            assertTrue(c.equals(cardInHand));
        }
    }

    @Test
    void handScorePlayerTest() {
        Card c = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        sut.add(c);
        assertEquals(10, sut.getScore());
    }

    @Test
    void handSizePlayerTest() {
        Deck deck = new Deck(new MockRandom());
        sut.add(deck.draw());
        assertEquals(1, sut.size());
    }
}
