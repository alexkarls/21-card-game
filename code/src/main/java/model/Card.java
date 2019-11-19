package model;

public class Card {

    public enum Color {
        CLUBS
    }

    public enum Value {
        TWO
    }

    public Card(Color color, Value value) {

    }

    public Color getColor() {
        return Color.CLUBS;
    }

    public Value getValue() {
        return Value.TWO;
    }

}
