import model.Card;
import model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    private Deck sut;
    private final int DECK_SIZE = 52;

    @BeforeEach
    void setup() {
        sut = new Deck();
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
}
