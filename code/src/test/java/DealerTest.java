import model.Card;
import model.Dealer;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    private Dealer sut;
    private Player dealer;
    private Player player;

    @BeforeEach
    void setup() {
        sut = new Dealer();
        dealer = new Player();
        player = new Player();
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void dealerIsLoserTest() {
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertFalse(sut.isLoser(player));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        // Added to better demonstrate the test/score (method tested in PlayerTest)
        assertEquals(22, player.getScore());
        assertTrue(sut.isLoser(player));
    }

    @Test
    void dealerIsWinnerOnScoreTest() {
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN));
        assertEquals(20, player.getScore()); // Added to better demonstrate the score
        assertFalse(sut.isWinner(player));
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        assertEquals(21, player.getScore());
        assertTrue(sut.isWinner(player));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        assertEquals(22, player.getScore());
        assertFalse(sut.isWinner(player));
    }

    @Test
    void dealerIsWinnerOnEqualTest() {
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.SPADES, Card.Rank.TWO));
        assertFalse(sut.isWinner(player));
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.THREE));
        assertTrue(sut.isWinner(player));
    }

    @Test
    void dealerReturnWinnerEqualTest() {
        dealer.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        assertEquals(dealer, sut.returnWinner(dealer, player));
    }

    @Test
    void dealerReturnWinnerTest() {
        dealer.add(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        player.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        assertEquals(player, sut.returnWinner(dealer, player));
        dealer.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        assertEquals(dealer, sut.returnWinner(dealer, player));
    }

    @Test
    void dealerReturnWinnerBustTest() {
        dealer.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        dealer.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertEquals(22, dealer.getScore());
        assertEquals(player, sut.returnWinner(dealer, player));
        player.add(new Card(Card.Suit.SPADES, Card.Rank.JACK));
        assertEquals(22, player.getScore());
        assertEquals(dealer, sut.returnWinner(dealer, player));
    }

}
