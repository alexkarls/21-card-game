import model.Card;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {

    private Dealer sut;

    @BeforeEach
    void setup() {
        sut = new Dealer();
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void isLoserDealerTest() {
        Player player = new Player();
        player.add(new Card(Card.Suit.SPADES, Card.Rank.KING));
        player.add(new Card(Card.Suit.HEARTS, Card.Rank.KING));
        assertTrue(sut.isLoser(player));
    }

    @Test
    void isWinnerDealerTest() {
        Player player = new Player();
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.SPADES, Card.Rank.TWO));
        player.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.THREE));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.THREE));
        // = 15 in score with 5 cards
        assertTrue(sut.isWinner(player));
    }

    @Test
    void returnWinnerDealerTest() {
        Player dealer = new Player();
        Player player = new Player();
        dealer.add(new Card(Card.Suit.SPADES, Card.Rank.TEN));
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        assertEquals(dealer, sut.returnWinner(dealer, player));
    }

}
