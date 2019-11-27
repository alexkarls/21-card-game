import controller.GameController;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RoundTest {

    private Round sut;
    private Rule rules;
    private Deck mockDeck;
    private Player dealer;
    private Player player;

    @BeforeEach

    void setup() {
        mockDeck = mock(Deck.class);
        rules = new Rule();
        dealer = new Player();
        player = new Player();
        sut = new Round(mockDeck, rules, dealer, player);
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void roundStartTest() {
        sut.start();
        int startHandSize = 2;
        assertEquals(startHandSize, player.size());
        verify(mockDeck, times(startHandSize)).draw();
    }

    @Test
    void roundEndTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        sut.playerTurn();
        sut.dealerTurn();
        assertEquals(sut.end(), Round.State.DEALER_WIN);                            // Equal score
        sut.playerTurn();
        assertEquals(sut.end(), Round.State.PLAYER_WIN);                            // Player more score (less than 21)
    }

    @Test
    void roundDealerTurnTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TEN));     // Less score than stand
        assertEquals(Round.State.UNKNOWN, sut.dealerTurn());
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));   // Stand score
        assertEquals(Round.State.DEALER_STAND, sut.dealerTurn());
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.FIVE));    // More than 21
        assertEquals(Round.State.PLAYER_WIN, sut.dealerTurn());
    }

    @Test
    void roundPlayerTurnWinSizeTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        for (int i = 0; i < 4; i++) {                                                   // 4 cards less than 21
            assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        }
        assertEquals(Round.State.PLAYER_WIN, sut.playerTurn());                         // 5 cards less than 21
    }

    @Test
    void roundPlayerTurnWinScoreTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());                            // Less than 21
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertEquals(Round.State.PLAYER_WIN, sut.playerTurn());                         // 21
    }

    @Test
    void roundPlayerTurnLoseTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());                            // Less than 21
        assertEquals(Round.State.DEALER_WIN, sut.playerTurn());                         // More than 21
    }

    @Test
    void roundGetDealerTest() {
        assertEquals(sut.getDealer(), dealer);
    }

    @Test
    void roundGetPlayerTest() {
        assertEquals(sut.getPlayer(), player);
    }

    @Test
    void roundSubscribeTest() {
        GameController observer = mock(GameController.class);
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        sut.subscribe(observer);
        sut.playerTurn();
        verify(observer, times(1)).update(player);
    }
}
