package model;

public class Card {

    public enum Color {
        CLUBS
    }

    public enum Value {
        TWO
    }

    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

}
