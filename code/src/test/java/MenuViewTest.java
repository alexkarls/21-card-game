import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import view.MenuAction;
import view.MenuView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuViewTest {

    MenuView sut;
    private final int INDEX_OFFSET = 1;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setup() {
        sut = new MenuView();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        System.out.println("Done with test: " + testInfo.getDisplayName());
    }

    @Test
    void menuViewGetInputActionTest() {
        String in = Integer.toString(MenuAction.EXIT.ordinal() + INDEX_OFFSET);
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        assertEquals(MenuAction.EXIT, sut.getInputAction());
    }

    @Test
    void menuViewDisplayMenuTest() {
        sut.displayMenu();
        String expected = "";
        for (MenuAction action : MenuAction.values()) {
            expected = expected + (action.ordinal() + 1) + ": " + action + System.lineSeparator();
        }
        assertEquals(expected, out.toString());
    }

}


