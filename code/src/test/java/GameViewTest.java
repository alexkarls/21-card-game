import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import view.GameAction;
import view.GameView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameViewTest {

    private GameView sut;
    private ByteArrayOutputStream out;
    private final int INDEX_OFFSET = 1;

    @BeforeEach
    void setup() {
        sut = new GameView();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void gameViewGetInputActionTest() {
        for (GameAction action : GameAction.values()) {
            int in = action.ordinal() + INDEX_OFFSET;
            System.setIn(new ByteArrayInputStream(Integer.toString(in).getBytes()));
            assertEquals(action, sut.getInputAction());
        }
    }

    @Test
    void menuViewGetInputActionReturnNullOnStringTest() {
        String in = "a";
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        assertEquals(null, sut.getInputAction());
    }

    @Test
    void menuViewGetInputActionReturnNullOnInvalidIntegerTest() {
        int in = GameAction.values()[GameAction.values().length - 1].ordinal();
        in = in +  INDEX_OFFSET + 1;
        System.setIn(new ByteArrayInputStream(Integer.toString(in).getBytes()));
        assertEquals(null, sut.getInputAction());
    }

    @Test
    void menuViewDisplayGameTest() {
        sut.displayGame();
        String expected = "";
        for (GameAction action : GameAction.values()) {
            expected = expected + (action.ordinal() + INDEX_OFFSET) + ": " + action + System.lineSeparator();
        }
        assertEquals(expected, out.toString());
    }
}
