import model.Card;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void cardCreateTest() {
        Card sut = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        assertEquals(Card.Suit.CLUBS, sut.getSuit());
        assertEquals(Card.Rank.TWO, sut.getRank());
    }

    @Test
    void cardEqualTest() {
        assertTrue(new Card(Card.Suit.CLUBS, Card.Rank.TWO).equals(new Card(Card.Suit.CLUBS, Card.Rank.TWO)));
        assertFalse(new Card(Card.Suit.CLUBS, Card.Rank.TWO).equals(new Card(Card.Suit.CLUBS, Card.Rank.THREE)));
    }
}
