package entities.phonetics;

public class Phoneme {
    private String symbol;
    private String soundClass;  //TODO: vowel/consonant


    public Phoneme(String symbol, int position) {
        this.symbol = symbol;
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

}
