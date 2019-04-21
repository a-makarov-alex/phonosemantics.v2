package entities.phonetics;

public class Vowel extends Phoneme {

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

    public Vowel(String symbol) {
        super(symbol);
    }

    public Vowel(String symbol, Height height, Backness backness, boolean roundedness, boolean nasalization) {
        super(symbol);
        this.height = height;
        this.backness = backness;
        this.roundedness = roundedness;
        this.nasalization = nasalization;
    }

    /********************************** GETTERS AND SETTERS *************************************/
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
