import model.Card;
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
    void createDeckSizeTest() {
        assertEquals(sut.size, DECK_SIZE);
    }

    @Test
    void createDeckCardTest() {
        for (Card.Color color : Card.Color.values()) {
            for (Card.Value value : Card.Value.values()) {
                Card c = sut.getCard();
                assertEquals(c.getColor, color);
                assertEquals(c.getValue, value);
            }
        }
    }

}
