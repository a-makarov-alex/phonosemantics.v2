package entities;

public class Vowel extends Phoneme {

    private String vowel;
    private Height height;
    private Backness backness;
    private boolean roundedness;
    private boolean nasalization;

    public enum Height {
        OPEN, OPEN_MID, CLOSE_MID, CLOSE
    }

    public enum Backness {
        BACK, CENTRAL, FRONT
    }

}
