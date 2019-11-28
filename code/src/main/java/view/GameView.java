package view;

import model.Card;
import model.Player;

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
        System.out.println();
        for (GameAction action : GameAction.values()) {
            System.out.println(action.ordinal() + INDEX_OFFSET + ": " + action.toString());
        }
        System.out.println();
    }

    public void displayPlayer(Player player, boolean isDealer) {
        System.out.println();
        if (isDealer) {
            System.out.println("Dealer:");
        } else {
            System.out.println("Player:");
        }
        for (Card c : player.getHand()) {
            displayCard(c);
        }
        System.out.println("Score: " + player.getScore());
    }

    private void displayCard(Card c) {
        System.out.println(c.getRank() + " of " + c.getSuit());
    }

    public void displayWinner(boolean isDealer) {
        System.out.println();
        if (isDealer) {
            System.out.println("DEALER WINS");
        } else {
            System.out.println("PLAYER WINS");
        }
        System.out.println();
    }
}
