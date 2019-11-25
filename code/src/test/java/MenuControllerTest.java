import controller.ControllerFactory;
import controller.GameController;
import controller.IController;
import controller.MenuController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import view.MenuAction;
import view.MenuView;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MenuControllerTest {

    private IController sut;
    private ControllerFactory mockControllers;
    private IController mockGameController;
    private final int INDEX_OFFSET = 1;

    @BeforeEach
    void setup() {
        mockControllers = mock(ControllerFactory.class);
        mockGameController = mock(GameController.class);
        when(mockGameController.run()).thenReturn(true);
        when(mockControllers.getController(ControllerFactory.Controller.GAME)).thenReturn(mockGameController);
        sut = new MenuController(mockControllers, new MenuView());
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void menuControllerViewDisplayTest() {
        MenuView mockView = mock(MenuView.class);
        when(mockView.getInputAction()).thenReturn(MenuAction.EXIT);
        String in = Integer.toString(MenuAction.EXIT.ordinal() + INDEX_OFFSET);
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        sut = new MenuController(mockControllers, mockView);
        sut.run();
        // Make sure all methods in MenuView are called...
        verify(mockView, times(1)).displayMenu();
        verify(mockView, times(1)).getInputAction();
    }


    @Test
    void menuControllerExitActionTest() {
        String in = Integer.toString(MenuAction.EXIT.ordinal() + INDEX_OFFSET);
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        assertFalse(sut.run());
    }

    @Test
    void menuControllerPlayActionTest() {
        String in = Integer.toString(MenuAction.PLAY.ordinal() + INDEX_OFFSET);
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        sut.run();
        verify(mockGameController, times(1)).run();
        in = Integer.toString(MenuAction.PLAY.ordinal() + INDEX_OFFSET);
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        sut.run();
        verify(mockGameController, times(2)).run();
    }

    // Assumes sut (MenuController) receives null on invalid input
    @Test
    void menuControllerNullMenuActionTest() {
        int in = MenuAction.values()[MenuAction.values().length - 1].ordinal();
        in = in +  INDEX_OFFSET + 1;
        System.setIn(new ByteArrayInputStream(Integer.toString(in).getBytes()));
        assertTrue(sut.run());
        // No calls should happen (should be broken into a method once more menu actions are added...)
        verify(mockGameController, times(0)).run();
    }

}
