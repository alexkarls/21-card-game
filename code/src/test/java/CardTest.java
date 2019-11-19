import model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void createCardsTest() {
        Card sut = new Card(Card.Color.CLUBS, Card.Value.TWO);
        assertEquals(sut.getColor(), Card.Color.CLUBS);
        assertEquals(sut.getValue(), Card.Value.TWO);
    }
}
