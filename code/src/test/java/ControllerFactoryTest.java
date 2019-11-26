import controller.ControllerFactory;
import controller.GameController;
import controller.IController;
import controller.MenuController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerFactoryTest {

    private ControllerFactory sut;

    @BeforeEach
    void setup() {
        sut = new ControllerFactory();
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void controllerFactoryGetFactoryTest() {
        IController expected = new MenuController(null, null);
        IController actual = sut.getController(ControllerFactory.Controller.MENU);
        assertEquals(expected.getClass(), actual.getClass());
        expected = new GameController(null, null);
        actual = sut.getController(ControllerFactory.Controller.GAME);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void controllerFactoryGetFactoryThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.getController(null);
        });
    }

    @Test
    void controllerInterfaceTest() {
        // Test that all controllers implement the interface method(s)...
        sut = mock(ControllerFactory.class);
        when(sut.getController(ControllerFactory.Controller.GAME)).thenReturn(mock(GameController.class));
        when(sut.getController(ControllerFactory.Controller.MENU)).thenReturn(mock(MenuController.class));
        GameController mockGameController = (GameController) sut.getController(ControllerFactory.Controller.GAME);
        MenuController mockMenuController = (MenuController) sut.getController(ControllerFactory.Controller.MENU);
        when(mockGameController.run()).thenReturn(true);
        when(mockMenuController.run()).thenReturn(true);
        assertTrue(mockGameController.run());
        assertTrue(mockMenuController.run());
    }
}
