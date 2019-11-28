package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {

    private final int INDEX_OFFSET = 1;

    public MenuAction getInputAction() {
        Scanner sc = new Scanner(System.in);
        try {
            int in = sc.nextInt() - INDEX_OFFSET;
            for (MenuAction action : MenuAction.values()) {
                if (in == action.ordinal()) {
                    return action;
                }
            }
        } catch (InputMismatchException e) {
            // Do nothing...
        }
        return null;
    }

    public void displayMenu() {
        for (MenuAction action : MenuAction.values()) {
            System.out.println(action.ordinal() + INDEX_OFFSET + ": " + action.toString());
        }
    }

}
