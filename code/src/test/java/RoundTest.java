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
    private Dealer rules;
    private Deck mockDeck;
    private Player dealer;
    private Player player;

    @BeforeEach

    void setup() {
        mockDeck = mock(Deck.class);
        rules = new Dealer();
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
        verify(mockDeck, times(startHandSize)).draw();
    }

    @Test
    void roundPlayerTurnWinSizeTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        assertEquals(Round.State.PLAYER_WIN, sut.playerTurn());
    }

    @Test
    void roundPlayerTurnWinScoreTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertEquals(Round.State.PLAYER_WIN, sut.playerTurn());
    }

    @Test
    void roundPlayerTurnLoseTest() {
        when(mockDeck.draw()).thenReturn(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertEquals(Round.State.UNKNOWN, sut.playerTurn());
        assertEquals(Round.State.DEALER_WIN, sut.playerTurn());
    }
}
