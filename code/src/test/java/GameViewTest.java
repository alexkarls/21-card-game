import model.Card;
import model.Player;
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
            setSystemInput(Integer.toString(action.ordinal() + INDEX_OFFSET));
            assertEquals(action, sut.getInputAction());
        }
    }

    @Test
    void menuViewGetInputActionReturnNullOnStringTest() {
        setSystemInput("a");
        assertEquals(null, sut.getInputAction());
    }

    @Test
    void menuViewGetInputActionReturnNullOnInvalidIntegerTest() {
        int lastIndex = GameAction.values()[GameAction.values().length - 1].ordinal();
        setSystemInput(Integer.toString(lastIndex + INDEX_OFFSET + 1));
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

    @Test
    void menuViewDisplayPlayerTest() {
        Player player = new Player();
        player.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));
        sut.displayPlayer(player, false);
        String expected = "";
        expected = expected + System.lineSeparator();
        expected = expected + "Player:" + System.lineSeparator();
        for (Card c : player.getHand()) {
            expected = expected + c.getRank() + " of " + c.getSuit();
        }
        System.out.println("Score: " + player.getScore());
        expected = expected + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    private void setSystemInput(String in) {
        System.setIn(new ByteArrayInputStream(in.getBytes()));
    }
}
