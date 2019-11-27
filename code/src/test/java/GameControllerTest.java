import controller.ControllerFactory;
import controller.GameController;
import model.Player;
import model.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.GameAction;
import view.GameView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    private GameController sut;
    private ControllerFactory mockControllers;
    private GameView mockView;
    private Round mockRound;


    @BeforeEach
    void setup() {
        mockControllers = mock(ControllerFactory.class);
        mockView = mock(GameView.class);
        mockRound = mock(Round.class);
        sut = new GameController(mockControllers, mockView);
        sut.set(mockRound);
    }

    @Test
    void gameControllerRunTest() {
        when(mockView.getInputAction()).thenReturn(GameAction.HIT);
        when(mockRound.playerTurn()).thenReturn(Round.State.DEALER_WIN);
        assertTrue(sut.run());
        when(mockRound.playerTurn()).thenReturn(Round.State.PLAYER_WIN);
        assertTrue(sut.run());
        when(mockRound.playerTurn()).thenReturn(Round.State.EXIT);
        assertFalse(sut.run());
    }

    @Test
    void gameControllerRunPlayerWinTest() {
        when(mockView.getInputAction()).thenReturn(GameAction.HIT);             // Player must HIT to reach playerTurn()
        when(mockRound.playerTurn()).thenReturn(Round.State.PLAYER_WIN);
        assertEquals(Round.State.PLAYER_WIN, sut.play(mockRound));
    }

    @Test
    void gameControllerPlayerLoseTest() {
        when(mockView.getInputAction()).thenReturn(GameAction.HIT);             // Player must HIT to reach playerTurn()
        when(mockRound.playerTurn()).thenReturn(Round.State.DEALER_WIN);
        assertEquals(Round.State.DEALER_WIN, sut.play(mockRound));
    }

    @Test
    void gameControllerRunDealerLostTest() {
        when(mockView.getInputAction()).thenReturn(GameAction.STAND);           // Player must STAND to reach automated dealerTurn()
        when(mockRound.dealerTurn()).thenReturn(Round.State.PLAYER_WIN);        // Should return immediately
        assertEquals(Round.State.PLAYER_WIN, sut.play(mockRound));
    }

    @Test
    void gameControllerPlayerVersusDealerTest() {
        when(mockView.getInputAction()).thenReturn(GameAction.STAND);
        when(mockRound.playerTurn()).thenReturn(Round.State.PLAYER_WIN);
        when(mockRound.dealerTurn()).thenReturn(Round.State.DEALER_STAND);      // Dealer must stand to reach end()
        when(mockRound.end()).thenReturn(Round.State.DEALER_WIN);
        assertEquals(Round.State.DEALER_WIN, sut.play(mockRound));
    }

    @Test
    void gameControllerRunShouldRThrowException() {
        when(mockRound.playerTurn()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> {
            sut.run();
        });
    }

    @Test
    void gameControllerIRoundObserverInterfaceTest() {
        sut = mock(GameController.class);
        Player dealer = new Player();
        Player player = new Player();
        sut.update(dealer);
        sut.update(player);
        verify(sut, times(1)).update(dealer);
        verify(sut, times(1)).update(player);
    }
}
