package view;

import java.util.Scanner;

public class MenuView {

    public MenuAction getInputAction() {
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
        return MenuAction.values()[in];
    }

    public void displayMenu() {
        for (MenuAction action : MenuAction.values()) {
            System.out.println(action.ordinal() + 1 + ": " + action.toString());
        }
    }


}
