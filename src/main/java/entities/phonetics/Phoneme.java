package entities.phonetics;

public class Phoneme {
    private String symbol;
    private int position;
    private String soundClass;  //TODO: vowel/consonant
    //private boolean phonation;  // TODO: parameters


    public Phoneme(String symbol, int position) {
        this.symbol = symbol;
        this.position = position;
    }

    public Phoneme(String symbol) {
        this.symbol = symbol;
    }

    public Phoneme() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
