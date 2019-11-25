package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameView {

    private final int INDEX_OFFSET = 1;

    public GameAction getInputAction() {
        Scanner sc = new Scanner(System.in);
        try {
            int in = sc.nextInt() - INDEX_OFFSET;
            for (GameAction action : GameAction.values()) {
                if (in == action.ordinal()) {
                    return action;
                }
            }
        } catch (InputMismatchException e) {
            // Do nothing...
        }
        return null;
    }

    public void displayGame() {
        for (GameAction action : GameAction.values()) {
            System.out.println(action.ordinal() + INDEX_OFFSET + ": " + action.toString());
        }
    }

}
