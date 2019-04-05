package entities;

public class Phoneme {
    private char symbol;
    private int position;
    private String soundClass;  //TODO: vowel/consonant
    private boolean phonation;  // TODO: parameters


    public Phoneme(char symbol, int position) {
        this.symbol = symbol;
        this.position = position;
    }

    public Phoneme() {
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
