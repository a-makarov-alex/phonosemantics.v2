package entities;

public class Vowel extends Phoneme {

    //private char vowel; //we have a symbol in a superclass TODO maybe delete field from here
    private Height height;
    private Backness backness;
    private boolean roundedness;
    private boolean nasalization;

    public enum Height {
        OPEN, NEAR_OPEN, OPEN_MID, MID, CLOSE_MID, NEAR_CLOSE, CLOSE
    }

    public enum Backness {
        BACK, CENTRAL, FRONT
    }

    public Vowel(char symbol) {
        super(symbol);
    }

    public Vowel(char symbol, Height height, Backness backness, boolean roundedness, boolean nasalization) {
        super(symbol);
        this.height = height;
        this.backness = backness;
        this.roundedness = roundedness;
        this.nasalization = nasalization;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Backness getBackness() {
        return backness;
    }

    public void setBackness(Backness backness) {
        this.backness = backness;
    }

    public boolean isRoundedness() {
        return roundedness;
    }

    public void setRoundedness(boolean roundedness) {
        this.roundedness = roundedness;
    }

    public boolean isNasalization() {
        return nasalization;
    }

    public void setNasalization(boolean nasalization) {
        this.nasalization = nasalization;
    }
}
