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
        when(sut.play(mockRound)).thenReturn(Round.State.DEALER_WIN);
        assertTrue(sut.run());
        when(sut.play(mockRound)).thenReturn(Round.State.PLAYER_WIN);
        assertTrue(sut.run());
        when(sut.play(mockRound)).thenReturn(Round.State.EXIT);
        assertFalse(sut.run());
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
