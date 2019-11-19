import model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void createCardsTest() {
        Card sut = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        assertEquals(sut.getSuit(), Card.Suit.CLUBS);
        assertEquals(sut.getRank(), Card.Rank.TWO);
    }
}
